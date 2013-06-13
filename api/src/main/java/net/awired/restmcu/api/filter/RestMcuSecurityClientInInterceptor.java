package net.awired.restmcu.api.filter;

import net.awired.jaxrs.client.server.resource.filter.FilterUtils;
import org.apache.cxf.message.Message;
import org.apache.cxf.phase.AbstractPhaseInterceptor;
import org.apache.cxf.phase.Phase;

public class RestMcuSecurityClientInInterceptor extends AbstractPhaseInterceptor<Message> {

    private RestMcuSecurityContext restMcuSecurityContext;

    public RestMcuSecurityClientInInterceptor(RestMcuSecurityKey key) {
        super(Phase.INVOKE);
        restMcuSecurityContext = new RestMcuSecurityContext(key);
    }

    @Override
    public void handleMessage(Message message) {
        try {
            restMcuSecurityContext.checkSecurity(message);
        } catch (SecurityException e) {
            FilterUtils.replaceCurrentPayloadWithError(message, e);
        }

    }

}
