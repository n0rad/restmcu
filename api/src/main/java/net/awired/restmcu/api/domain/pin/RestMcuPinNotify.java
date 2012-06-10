package net.awired.restmcu.api.domain.pin;


public class RestMcuPinNotify {

    private RestMcuPinNotifyCondition notifyCondition;
    private float notifyValue;

    public RestMcuPinNotify() {
    }

    public RestMcuPinNotify(RestMcuPinNotifyCondition notifyCondition, float notifyValue) {
        this.notifyCondition = notifyCondition;
        this.notifyValue = notifyValue;
    }

    public RestMcuPinNotifyCondition getNotifyCondition() {
        return notifyCondition;
    }

    public void setNotifyCondition(RestMcuPinNotifyCondition notifyCondition) {
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
        RestMcuPinNotify other = (RestMcuPinNotify) obj;
        if (notifyCondition != other.notifyCondition) {
            return false;
        }
        if (Float.floatToIntBits(notifyValue) != Float.floatToIntBits(other.notifyValue)) {
            return false;
        }
        return true;
    }

}
