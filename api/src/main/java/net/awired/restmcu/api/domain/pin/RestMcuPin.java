package net.awired.restmcu.api.domain.pin;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "pin")
@XmlAccessorType(XmlAccessType.FIELD)
public class RestMcuPin {

    private String name; // updatable
    private String description;
    private RestMcuPinDirection direction;
    private RestMcuPinType type;
    private Float valueMin;
    private Float valueMax;

    private List<RestMcuPinNotify> notifies; // input only, updatable

    public void addNotify(RestMcuPinNotify notify) {
        if (this.notifies == null) {
            this.notifies = new ArrayList<RestMcuPinNotify>();
        }
        this.notifies.add(notify);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<RestMcuPinNotify> getNotifies() {
        return notifies;
    }

    public void setNotifies(List<RestMcuPinNotify> notifies) {
        this.notifies = notifies;
    }

    public RestMcuPinDirection getDirection() {
        return direction;
    }

    public RestMcuPinType getType() {
        return type;
    }

    public void setType(RestMcuPinType type) {
        this.type = type;
    }

    public void setDirection(RestMcuPinDirection direction) {
        this.direction = direction;
    }

    public void setValueMin(Float valueMin) {
        this.valueMin = valueMin;
    }

    public Float getValueMin() {
        return valueMin;
    }

    public void setValueMax(Float valueMax) {
        this.valueMax = valueMax;
    }

    public Float getValueMax() {
        return valueMax;
    }

}
