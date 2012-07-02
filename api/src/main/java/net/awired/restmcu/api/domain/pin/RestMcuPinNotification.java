package net.awired.restmcu.api.domain.pin;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import com.google.common.base.Objects;

@XmlRootElement(name = "pinNotification")
@XmlAccessorType(XmlAccessType.FIELD)
public class RestMcuPinNotification {

    private int id;
    private float oldValue;
    private float value;
    private String source;
    private RestMcuPinNotify notify;

    @Override
    public String toString() {
        return Objects.toStringHelper(this) //
                .add("id", id) //
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

    public RestMcuPinNotify getNotify() {
        return notify;
    }

    public void setNotify(RestMcuPinNotify notify) {
        this.notify = notify;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }
}
