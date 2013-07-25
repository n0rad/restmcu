/**
 *
 *     Copyright (C) norad.fr
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

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import org.apache.cxf.message.Message;
import org.apache.cxf.phase.AbstractPhaseInterceptor;
import org.apache.cxf.phase.Phase;

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
