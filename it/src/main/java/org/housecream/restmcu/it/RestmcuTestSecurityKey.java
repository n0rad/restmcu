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
package org.housecream.restmcu.it;

import org.apache.cxf.message.Message;
import org.housecream.restmcu.api.filter.RestMcuSecurityKey;

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
