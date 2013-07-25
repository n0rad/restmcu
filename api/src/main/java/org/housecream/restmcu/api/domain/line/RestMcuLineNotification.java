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
package org.housecream.restmcu.api.domain.line;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import com.google.common.base.Objects;

@XmlRootElement(name = "lineNotification")
@XmlAccessorType(XmlAccessType.FIELD)
public class RestMcuLineNotification {

    private int lineId;
    private float oldValue;
    private float value;
    /** ex: 127.0.0.1:5879 */
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

    @Override
    public int hashCode() {
        return Objects.hashCode(lineId, oldValue, value, source, notify);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final RestMcuLineNotification other = (RestMcuLineNotification) obj;
        return Objects.equal(this.lineId, other.lineId) //
                && Objects.equal(this.oldValue, other.oldValue) //
                && Objects.equal(this.value, other.value) //
                && Objects.equal(this.source, other.source) //
                && Objects.equal(this.notify, other.notify);
    }

    @Override
    public String toString() {
        return Objects.toStringHelper(this) //
                .add("lineId", lineId) //
                .add("oldValue", oldValue) //
                .add("value", value) //
                .add("source", source) //
                .add("notify", notify) //
                .toString();
    }

    public float getOldValue() {
        return oldValue;
    }

    public void setOldValue(float oldValue) {
        this.oldValue = oldValue;
    }

    public float getValue() {
        return value;
    }

    public void setValue(float value) {
        this.value = value;
    }

    public RestMcuLineNotify getNotify() {
        return notify;
    }

    public void setNotify(RestMcuLineNotify notify) {
        this.notify = notify;
    }

    public void setId(int id) {
        this.lineId = id;
    }

    public int getId() {
        return lineId;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }
}
