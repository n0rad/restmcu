package net.awired.restmcu.api.filter;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collection;
import java.util.TreeMap;
import javax.ws.rs.core.Response;
import net.awired.restmcu.api.resource.server.RestMcuNotifyResource;
import org.apache.cxf.jaxrs.utils.JAXRSUtils;
import org.apache.cxf.message.Message;
import org.apache.cxf.phase.Phase;

public class RestMcuSecurityInInterceptor extends RestMcuInterceptor {

    private long maxValidWindowSecondOneSide = 60; // window of 2x1min

    public RestMcuSecurityInInterceptor(RestMcuSecurityKey key) {
        super(Phase.PRE_INVOKE, key);
    }

    @Override
    public void handleMessage(Message message) {
        if (!isSecurityNeeded(message)) {
            return;
        }

        @SuppressWarnings("unchecked")
        TreeMap<String, ArrayList<String>> headers = (TreeMap<String, ArrayList<String>>) message
                .get(Message.PROTOCOL_HEADERS);

        try {

            long extractTime = extractTime(headers);
            if (!isValidWindow(extractTime)) {
                throw new RuntimeException("time overflow");
            }

            String generatedHash = buildHash(extractTime, message);
            if (!generatedHash.toUpperCase().equals(extractHash(headers).toUpperCase())) {
                throw new SecurityException("Hmac-Hash does not match");
            }

        } catch (Exception ex) {
            Response excResponse = JAXRSUtils.convertFaultToResponse(ex, message);
            //        if (excResponse == null) {
            //        ProviderFactory.getInstance(message).clearThreadLocalProxies();
            //        }
            message.getExchange().put(Response.class, excResponse);
        }

    }

    protected String extractHash(TreeMap<String, ArrayList<String>> headers) {
        Collection<String> hash = headers.get("Hmac-Hash");
        if (hash == null) {
            throw new SecurityException("no Hmac-Hash header");
        }
        return hash.iterator().next();
    }

    protected long extractTime(TreeMap<String, ArrayList<String>> headers) {
        Collection<String> posixTimes = headers.get("Hmac-Time");
        if (posixTimes == null) {
            throw new SecurityException("no Hmac-Time header");
        }
        try {
            return Long.parseLong(posixTimes.iterator().next());
        } catch (Exception e) {
            throw new SecurityException("bad Hmac-Time header");
        }
    }

    protected boolean isSecurityNeeded(Message message) {
        Method method = (Method) message.get("org.apache.cxf.resource.method");
        if (method == null || method.getDeclaringClass().isAssignableFrom(RestMcuNotifyResource.class)) {
            return false;
        }

        boolean isTimeCall = message.get("org.apache.cxf.message.Message.PATH_INFO").equals("/time");
        if (isTimeCall) {
            return false;
        }
        return true;
    }

    protected boolean isValidWindow(long posixTime) {
        long currentTimeMillis = System.currentTimeMillis() / 1000L;
        return posixTime > currentTimeMillis - maxValidWindowSecondOneSide
                && posixTime < currentTimeMillis + maxValidWindowSecondOneSide;
    }

}
