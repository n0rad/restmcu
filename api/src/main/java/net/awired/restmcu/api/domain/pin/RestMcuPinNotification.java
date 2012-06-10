package net.awired.restmcu.api.domain.pin;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "pinNotification")
@XmlAccessorType(XmlAccessType.FIELD)
public class RestMcuPinNotification {

    private int pinId;
    private float oldValue;
    private float value;
    private String source;
    private RestMcuPinNotify notify;

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

    public RestMcuPinNotify getNotify() {
        return notify;
    }

    public void setNotify(RestMcuPinNotify notify) {
        this.notify = notify;
    }

    public void setPinId(int pinId) {
        this.pinId = pinId;
    }

    public int getPinId() {
        return pinId;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }
}
