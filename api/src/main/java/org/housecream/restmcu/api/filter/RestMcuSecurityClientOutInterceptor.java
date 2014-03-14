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
public class RestMcuSecurityClientOutInterceptor extends AbstractPhaseInterceptor<Message> {
    private RestMcuSecurityContext restMcuSecurityContext;

    public RestMcuSecurityClientOutInterceptor(RestMcuSecurityKey key) {
        super(Phase.WRITE);
        restMcuSecurityContext = new RestMcuSecurityContext(key);
    }

    @Override
    public void handleMessage(Message message) {
        long time = System.currentTimeMillis() / 1000L;
        @SuppressWarnings("unchecked")
        Map<String, List<String>> headers = (Map<String, List<String>>) message.get(Message.PROTOCOL_HEADERS);
        if (headers == null) {
            headers = new TreeMap<String, List<String>>(String.CASE_INSENSITIVE_ORDER);
            message.put(Message.PROTOCOL_HEADERS, headers);
        }
        headers.put("Hmac-Time", Arrays.asList(String.valueOf(time)));
        headers.put("Hmac-Hash", Arrays.asList(restMcuSecurityContext.buildHash(time, message)));
    }
}
*/
