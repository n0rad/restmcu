package net.awired.restmcu.it.builder;

import net.awired.restmcu.api.domain.line.RestMcuLineNotification;
import net.awired.restmcu.api.domain.line.RestMcuLineNotify;
import net.awired.restmcu.api.domain.line.RestMcuLineNotifyCondition;
import net.awired.restmcu.it.resource.LineInfo;

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
