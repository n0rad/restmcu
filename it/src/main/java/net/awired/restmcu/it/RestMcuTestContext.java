package net.awired.restmcu.it;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import javax.ws.rs.core.MediaType;
import net.awired.ajsl.web.resource.mapper.AjslResponseExceptionMapper;
import org.apache.cxf.binding.BindingFactoryManager;
import org.apache.cxf.interceptor.LoggingInInterceptor;
import org.apache.cxf.interceptor.LoggingOutInterceptor;
import org.apache.cxf.jaxrs.JAXRSBindingFactory;
import org.apache.cxf.jaxrs.client.JAXRSClientFactoryBean;
import org.apache.cxf.jaxrs.client.WebClient;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.jaxrs.json.JacksonJaxbJsonProvider;

public class RestMcuTestContext {

    private AjslResponseExceptionMapper responseExceptionMapper;
    private LoggingInInterceptor inLogger;
    private LoggingOutInterceptor outLogger;
    private JacksonJaxbJsonProvider jacksonJaxbJsonProvider;

    public RestMcuTestContext() {
        inLogger = new LoggingInInterceptor();
        inLogger.setPrettyLogging(true);
        inLogger.setOutputLocation("<stdout>");
        outLogger = new LoggingOutInterceptor();
        outLogger.setPrettyLogging(true);
        outLogger.setOutputLocation("<stderr>");

        ObjectMapper mapper = new ObjectMapper();
        mapper.setSerializationInclusion(Include.NON_NULL);
        jacksonJaxbJsonProvider = new JacksonJaxbJsonProvider();
        jacksonJaxbJsonProvider.setMapper(mapper);
        responseExceptionMapper = new AjslResponseExceptionMapper(jacksonJaxbJsonProvider);
    }

    private static final String BOARD_HOST_DEFAULT = "192.168.42.244";
    private static final String BOARD_HOST_PROPERTY_NAME = "board.host";

    private static final String BOARD_PORT_DEFAULT = "80";
    private static final String BOARD_PORT_PROPERTY_NAME = "board.port";

    private static final String BOARD_PATH_DEFAULT = "/";
    private static final String BOARD_PATH_PROPERTY_NAME = "board.path";

    public static String getUrl() {
        return "http://" + System.getProperty(BOARD_HOST_PROPERTY_NAME, BOARD_HOST_DEFAULT) //
                + ":" + System.getProperty(BOARD_PORT_PROPERTY_NAME, BOARD_PORT_DEFAULT) //
                + System.getProperty(BOARD_PATH_PROPERTY_NAME, BOARD_PATH_DEFAULT);
    }

    public <T> T buildResourceProxy(Class<T> clazz, String url) {
        JAXRSClientFactoryBean sf = new JAXRSClientFactoryBean();
        sf.setProviders(Arrays.asList(jacksonJaxbJsonProvider, responseExceptionMapper));
        sf.getInInterceptors().add(inLogger);
        sf.getOutInterceptors().add(outLogger);
        sf.setResourceClass(clazz);
        sf.setAddress(url);

        Map<String, String> headers = new HashMap<String, String>();
        //        headers.put("Cookie", "JSESSIONID=" + session.getSessionId());
        //        headers.put("Cookie2", "$Version=1");
        sf.setHeaders(headers);

        BindingFactoryManager manager = sf.getBus().getExtension(BindingFactoryManager.class);
        JAXRSBindingFactory factory = new JAXRSBindingFactory();
        factory.setBus(sf.getBus());
        manager.registerBindingFactory(JAXRSBindingFactory.JAXRS_BINDING_ID, factory);
        T service = sf.create(clazz);
        WebClient.client(service).accept(MediaType.APPLICATION_JSON_TYPE).type(MediaType.APPLICATION_JSON_TYPE);
        return service;
    }
}
