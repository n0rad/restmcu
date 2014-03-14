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
package org.housecream.restmcu.api.domain.line;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import lombok.Data;

@Data
@XmlRootElement(name = "lineNotification")
@XmlAccessorType(XmlAccessType.FIELD)
public class RestMcuLineNotification {

    private int lineId;
    private float oldValue;
    private float value;
    /**
     * ex: 127.0.0.1:5879
     */
    private String source;
    private RestMcuLineNotify notify;

    public RestMcuLineNotification() {
    }

    public RestMcuLineNotification(int lineId, float oldValue, float value, RestMcuLineNotify notify) {
        this.lineId = lineId;
        this.oldValue = oldValue;
        this.value = value;
        this.notify = notify;
    }

}
