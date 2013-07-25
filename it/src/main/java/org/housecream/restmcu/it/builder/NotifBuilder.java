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
package org.housecream.restmcu.it.builder;

import org.housecream.restmcu.api.domain.line.RestMcuLineNotification;
import org.housecream.restmcu.api.domain.line.RestMcuLineNotify;
import org.housecream.restmcu.api.domain.line.RestMcuLineNotifyCondition;
import org.housecream.restmcu.it.resource.LineInfo;

public class NotifBuilder {

    private float value;
    private float oldValue;
    private Integer lineId;
    private RestMcuLineNotify notify;
    private LineInfo lineInfo;

    public static NotifBuilder notif() {
        return new NotifBuilder();
    }

    public static NotifBuilder notif(LineInfo lineInfo) {
        NotifBuilder notifBuilder = new NotifBuilder();
        notifBuilder.line(lineInfo);
        return notifBuilder;
    }

    public RestMcuLineNotification build() {
        RestMcuLineNotification pinNotif = new RestMcuLineNotification();
        if (lineId == null) {
            pinNotif.setId(lineInfo.getLineId());
            pinNotif.setOldValue(lineInfo.getValue());
        } else {
            pinNotif.setId(lineId);
            pinNotif.setOldValue(oldValue);
        }
        pinNotif.setValue(value);
        pinNotif.setNotify(notify);
        return pinNotif;
    }

    public NotifBuilder line(LineInfo lineInfo) {
        this.lineInfo = lineInfo;
        return this;
    }

    public NotifBuilder val(float value) {
        this.value = value;
        return this;
    }

    public NotifBuilder oldVal(float oldValue) {
        this.oldValue = oldValue;
        return this;
    }

    public NotifBuilder notify(RestMcuLineNotifyCondition supOrEqual, int i) {
        this.notify = new RestMcuLineNotify(supOrEqual, i);
        return this;
    }

    public NotifBuilder lineId(Integer lineId) {
        this.lineId = lineId;
        return this;
    }
}
