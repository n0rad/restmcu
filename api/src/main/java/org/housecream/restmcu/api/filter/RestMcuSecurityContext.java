/**
 *
 *     Copyright (C) Awired.net
 *
 *     Licensed under the Apache License, Version 2.0 (the "License");
 *     you may not use this file except in compliance with the License.
 *     You may obtain a copy of the License at
 *
 *             http://www.apache.org/licenses/LICENSE-2.0
 *
 *     Unless required by applicable law or agreed to in writing, software
 *     distributed under the License is distributed on an "AS IS" BASIS,
 *     WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *     See the License for the specific language governing permissions and
 *     limitations under the License.
 */
package org.housecream.restmcu.api.filter;

import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Collection;
import java.util.List;
import java.util.TreeMap;
import javax.crypto.Mac;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import org.apache.cxf.message.Message;
import com.google.common.base.Preconditions;

public class RestMcuSecurityContext {

    private long maxValidWindowSecondOneSide = 60; // window of 2x1min
    private RestMcuSecurityKey key;
    private SecretKey signingKey;

    public RestMcuSecurityContext(RestMcuSecurityKey key) {
        Preconditions.checkNotNull(key, "key cannot be null");
        signingKey = new SecretKeySpec(key.getKey().getBytes(), "HMACSHA1");
        this.key = key;
    }

    public String buildHash(long time, Message message) {
        try {
            Mac mac = Mac.getInstance("HMACSHA1");
            mac.init(signingKey);
            String buildMessage = key.buildMessage(time, message);
            if (buildMessage == null) {
                throw new IllegalStateException("build of message with " + key + " return null");
            }
            byte[] digest = mac.doFinal(buildMessage.getBytes("ASCII"));
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

    public void checkSecurity(Message message) throws SecurityException {
        @SuppressWarnings("unchecked")
        TreeMap<String, List<String>> headers = (TreeMap<String, List<String>>) message.get(Message.PROTOCOL_HEADERS);
        long extractTime = extractTime(headers);

        if (!isValidWindow(extractTime)) {
            throw new SecurityException("Hmac-Time overflow");
        }

        String generatedHash = buildHash(extractTime, message);
        if (!generatedHash.equals(extractHash(headers))) {
            throw new SecurityException("Hmac-Hash does not match");
        }
    }

    private boolean isValidWindow(long posixTime) {
        long currentTimeMillis = System.currentTimeMillis() / 1000L;
        return posixTime > currentTimeMillis - maxValidWindowSecondOneSide
                && posixTime < currentTimeMillis + maxValidWindowSecondOneSide;
    }

    private long extractTime(TreeMap<String, List<String>> headers) {
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

    private String extractHash(TreeMap<String, List<String>> headers) {
        Collection<String> hash = headers.get("Hmac-Hash");
        if (hash == null) {
            throw new SecurityException("no Hmac-Hash header");
        }
        return hash.iterator().next();
    }

}
