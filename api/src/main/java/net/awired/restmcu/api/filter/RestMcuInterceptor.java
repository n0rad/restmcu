package net.awired.restmcu.api.filter;

import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import javax.crypto.Mac;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import org.apache.cxf.message.Message;
import org.apache.cxf.phase.AbstractPhaseInterceptor;
import com.google.common.base.Preconditions;

public abstract class RestMcuInterceptor extends AbstractPhaseInterceptor<Message> {

    private RestMcuSecurityKey key;
    private SecretKey signingKey;

    protected RestMcuInterceptor(String phase, RestMcuSecurityKey key) {
        super(phase);
        Preconditions.checkNotNull(key, "key cannot be null");
        signingKey = new SecretKeySpec(key.getKey().getBytes(), "HMACSHA1");
        this.key = key;
    }

    protected String buildHash(long time, Message message) {
        try {
            Mac mac = Mac.getInstance("HMACSHA1");
            mac.init(signingKey);
            byte[] digest = mac.doFinal(key.buildMessage(time, message).getBytes("ASCII"));
            StringBuilder sb = new StringBuilder();
            for (byte b : digest) {
                sb.append(String.format("%02x", b));
            }
            return sb.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new IllegalStateException("HMACSHA1 not found", e);
        } catch (InvalidKeyException e) {
            throw new IllegalStateException("Bad key", e);
        } catch (UnsupportedEncodingException e) {
            throw new IllegalStateException("Unsupported encoding", e);
        }
    }

}
