package net.awired.restmcu.api.filter;

import net.awired.ajsl.web.resource.filter.AjslFilterUtils;
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
            AjslFilterUtils.replaceCurrentPayloadWithError(message, e);
        }

    }

}
