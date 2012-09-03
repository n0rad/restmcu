package net.awired.restmcu.api.filter;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.lang.annotation.Annotation;
import java.util.Arrays;
import java.util.List;
import java.util.TreeMap;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.MessageBodyWriter;
import org.apache.cxf.jaxrs.provider.ProviderFactory;
import org.apache.cxf.jaxrs.utils.JAXRSUtils;
import org.apache.cxf.message.Message;
import org.apache.cxf.phase.AbstractPhaseInterceptor;
import org.apache.cxf.phase.Phase;

public class RestMcuSecurityClientInInterceptor extends AbstractPhaseInterceptor<Message> {

    private RestMcuSecurityContext restMcuSecurityContext;

    public RestMcuSecurityClientInInterceptor(RestMcuSecurityKey key) {
        super(Phase.INVOKE);
        restMcuSecurityContext = new RestMcuSecurityContext(key);
    }

    /**
     * That sux but I cannot found another way to do it
     */
    private byte[] buildErrorPayload(Response excResponse, Message message) {
        MessageBodyWriter<net.awired.ajsl.web.resource.Error> createMessageBodyWriter = ProviderFactory.getInstance(
                message).createMessageBodyWriter(net.awired.ajsl.web.resource.Error.class,
                net.awired.ajsl.web.resource.Error.class, new Annotation[] {}, MediaType.APPLICATION_JSON_TYPE,
                message);
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();

        try {
            createMessageBodyWriter.writeTo((net.awired.ajsl.web.resource.Error) excResponse.getEntity(),
                    net.awired.ajsl.web.resource.Error.class, net.awired.ajsl.web.resource.Error.class,
                    new Annotation[] {}, MediaType.APPLICATION_JSON_TYPE, excResponse.getMetadata(),
                    byteArrayOutputStream);
            return byteArrayOutputStream.toByteArray();
        } catch (Exception ex) {
            throw new RuntimeException("cannot build error payload for response : " + excResponse, ex);
        }
    }

    @Override
    public void handleMessage(Message message) {
        try {
            restMcuSecurityContext.checkSecurity(message);
        } catch (SecurityException e) {
            @SuppressWarnings("unchecked")
            TreeMap<String, List<String>> headers = (TreeMap<String, List<String>>) message
                    .get(Message.PROTOCOL_HEADERS);

            Response excResponse = JAXRSUtils.convertFaultToResponse(e, message);
            message.setContent(InputStream.class, new ByteArrayInputStream(buildErrorPayload(excResponse, message)));
            message.getExchange().put(Message.RESPONSE_CODE, excResponse.getStatus());
            headers.put("content-type", Arrays.asList(MediaType.APPLICATION_JSON));

        }

    }

}
