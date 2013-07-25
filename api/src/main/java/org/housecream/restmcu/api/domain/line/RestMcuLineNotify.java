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
package org.housecream.restmcu.api.domain.line;

import com.google.common.base.Objects;

public class RestMcuLineNotify {

    private RestMcuLineNotifyCondition notifyCondition;
    private float notifyValue;

    public RestMcuLineNotify() {
    }

    public RestMcuLineNotify(RestMcuLineNotifyCondition notifyCondition, float notifyValue) {
        this.notifyCondition = notifyCondition;
        this.notifyValue = notifyValue;
    }

    @Override
    public String toString() {
        return Objects.toStringHelper(this) //
                .add("notifyCondition", notifyCondition) //
                .add("oldValue", notifyValue) //
                .toString();
    }

    public RestMcuLineNotifyCondition getNotifyCondition() {
        return notifyCondition;
    }

    public void setNotifyCondition(RestMcuLineNotifyCondition notifyCondition) {
        this.notifyCondition = notifyCondition;
    }

    public float getNotifyValue() {
        return notifyValue;
    }

    public void setNotifyValue(float notifyValue) {
        this.notifyValue = notifyValue;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((notifyCondition == null) ? 0 : notifyCondition.hashCode());
        result = prime * result + Float.floatToIntBits(notifyValue);
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        RestMcuLineNotify other = (RestMcuLineNotify) obj;
        if (notifyCondition != other.notifyCondition) {
            return false;
        }
        if (Float.floatToIntBits(notifyValue) != Float.floatToIntBits(other.notifyValue)) {
            return false;
        }
        return true;
    }

}
