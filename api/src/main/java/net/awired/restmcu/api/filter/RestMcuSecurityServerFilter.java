package net.awired.restmcu.api.filter;

import java.lang.reflect.Method;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.Provider;
import net.awired.restmcu.api.resource.server.RestMcuNotifyResource;
import org.apache.cxf.jaxrs.ext.RequestHandler;
import org.apache.cxf.jaxrs.ext.ResponseHandler;
import org.apache.cxf.jaxrs.model.ClassResourceInfo;
import org.apache.cxf.jaxrs.model.OperationResourceInfo;
import org.apache.cxf.message.Message;

@Provider
public class RestMcuSecurityServerFilter implements RequestHandler, ResponseHandler {

    private RestMcuSecurityContext restMcuSecurityContext;

    public RestMcuSecurityServerFilter(RestMcuSecurityKey key) {
        restMcuSecurityContext = new RestMcuSecurityContext(key);
    }

    @Override
    public Response handleResponse(Message message, OperationResourceInfo opResourceInfo, Response response) {
        try {
            long time = System.currentTimeMillis() / 1000L;
            response.getMetadata().add("Hmac-Time", time);
            response.getMetadata().add("Hmac-Hash", restMcuSecurityContext.buildHash(time, message));
        } catch (Exception e) {
            return Response.status(Status.INTERNAL_SERVER_ERROR).build();
        }

        return response;
    }

    private boolean isSecurityNeeded(Message message) {
        Method method = (Method) message.get("org.apache.cxf.resource.method");
        if (method == null || !RestMcuNotifyResource.class.isAssignableFrom(method.getDeclaringClass())) {
            return false;
        }
        boolean isTimeCall = message.get(Message.PATH_INFO).equals("/time");
        if (isTimeCall) {
            return false;
        }
        return true;
    }

    @Override
    public Response handleRequest(Message message, ClassResourceInfo classResourceInfo) {
        if (isSecurityNeeded(message)) {
            restMcuSecurityContext.checkSecurity(message);
        }
        return null;
    }

}
