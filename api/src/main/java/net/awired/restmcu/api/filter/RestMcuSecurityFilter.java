package net.awired.restmcu.api.filter;

import java.io.UnsupportedEncodingException;
import java.lang.reflect.Method;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.TreeMap;
import javax.crypto.Mac;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.Provider;
import net.awired.restmcu.api.resource.server.RestMcuNotifyResource;
import org.apache.cxf.jaxrs.ext.RequestHandler;
import org.apache.cxf.jaxrs.ext.ResponseHandler;
import org.apache.cxf.jaxrs.model.ClassResourceInfo;
import org.apache.cxf.jaxrs.model.OperationResourceInfo;
import org.apache.cxf.jaxrs.utils.JAXRSUtils;
import org.apache.cxf.message.Message;
import com.google.common.base.Preconditions;

@Provider
public class RestMcuSecurityFilter implements RequestHandler, ResponseHandler {

    private long maxValidWindowMilliSecondOneSide = 1000 * 120; // window of 2x2min
    private RestMcuSecurityKey key;
    private SecretKey signingKey;

    public RestMcuSecurityFilter(RestMcuSecurityKey key) {
        Preconditions.checkNotNull(key, "key cannot be null");
        signingKey = new SecretKeySpec(key.getKey().getBytes(), "HMACSHA256");
        this.key = key;
    }

    public RestMcuSecurityFilter(RestMcuSecurityKey key, long maxValidWindowMilliSecond) {
        this(key);
        this.maxValidWindowMilliSecondOneSide = maxValidWindowMilliSecond / 2;
    }

    @Override
    public Response handleResponse(Message message, OperationResourceInfo opResourceInfo, Response response) {
        try {
            response.getMetadata().add("Hmac-Time", System.currentTimeMillis());
            response.getMetadata().add("Hmac-Hash", buildHash(message));
        } catch (Exception e) {
            return Response.status(Status.INTERNAL_SERVER_ERROR).build();
        }

        return response;
    }

    @Override
    public Response handleRequest(Message message, ClassResourceInfo classResourceInfo) {
        if (!isSecurityNeeded(message)) {
            return null;
        }

        @SuppressWarnings("unchecked")
        TreeMap<String, ArrayList<String>> headers = (TreeMap<String, ArrayList<String>>) message
                .get(Message.PROTOCOL_HEADERS);

        if (isInvalidWindow(extractTime(headers))) {
            System.out.println("overflow");
            RuntimeException exception = new RuntimeException("time overflow");
            return JAXRSUtils.convertFaultToResponse(exception, message);
        }

        String generatedHash = buildHash(message);
        if (!generatedHash.toUpperCase().equals(extractHash(headers).toUpperCase())) {
            throw new SecurityException("Hmac-Hash does not match");
        }
        return null;
    }

    private String buildHash(Message message) {
        try {
            Mac mac = Mac.getInstance("HMACSHA256");
            mac.init(signingKey);
            byte[] digest = mac.doFinal("MESSAGE".getBytes("ASCII"));
            StringBuilder sb = new StringBuilder();
            for (byte b : digest) {
                sb.append(String.format("%02x", b));
            }
            return sb.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new IllegalStateException("HMACSHA256 not found", e);
        } catch (InvalidKeyException e) {
            throw new IllegalStateException("Bad key", e);
        } catch (UnsupportedEncodingException e) {
            throw new IllegalStateException("Unsupported encoding", e);
        }
    }

    private String extractHash(TreeMap<String, ArrayList<String>> headers) {
        ArrayList<String> hash = headers.get("Hmac-Hash");
        if (hash == null) {
            throw new SecurityException("no Hmac-Hash header");
        }
        return hash.get(0);
    }

    private int extractTime(TreeMap<String, ArrayList<String>> headers) {
        ArrayList<String> times = headers.get("Hmac-Time");
        if (times == null) {
            throw new SecurityException("no Hmac-Time header");
        }
        try {
            int time = Integer.parseInt(times.get(0));
            return time;
        } catch (Exception e) {
            throw new SecurityException("bad Hmac-Time header");
        }
    }

    private boolean isSecurityNeeded(Message message) {
        Method method = (Method) message.get("org.apache.cxf.resource.method");
        if (method.getDeclaringClass().isAssignableFrom(RestMcuNotifyResource.class)) {
            return false;
        }

        boolean isTimeCall = message.get("org.apache.cxf.message.Message.PATH_INFO").equals("/time");
        if (isTimeCall) {
            return false;
        }
        return true;
    }

    private boolean isInvalidWindow(int time) {
        long currentTimeMillis = System.currentTimeMillis();
        return time + maxValidWindowMilliSecondOneSide > currentTimeMillis
                || time - maxValidWindowMilliSecondOneSide < currentTimeMillis;
    }
}
