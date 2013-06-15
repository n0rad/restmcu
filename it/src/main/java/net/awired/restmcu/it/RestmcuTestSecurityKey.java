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
package net.awired.restmcu.it;

import net.awired.restmcu.api.filter.RestMcuSecurityKey;
import org.apache.cxf.message.Message;

public class RestmcuTestSecurityKey implements RestMcuSecurityKey {

    private static final String key = "TOTO42";

    @Override
    public String getKey() {
        return key;
    }

    @Override
    public String buildMessage(long posixTimestamp, Message message) {
        return posixTimestamp + "MESSAGE";
    }

}
