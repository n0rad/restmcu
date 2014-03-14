/**
 *
 *     Copyright (C) Housecream.org
 *
 *     This program is free software: you can redistribute it and/or modify
 *     it under the terms of the GNU Affero General Public License as published by
 *     the Free Software Foundation, either version 3 of the License, or any later version.
 *
 *     This program is distributed in the hope that it will be useful,
 *     but WITHOUT ANY WARRANTY; without even the implied warranty of
 *     MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *     GNU Affero General Public License for more details.
 *
 *     You should have received a copy of the GNU Affero General Public License
 *     along with this program.  If not, see <http://www.gnu.org/licenses/>
 */
package org.housecream.restmcu.api.filter;

/*
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
*/
