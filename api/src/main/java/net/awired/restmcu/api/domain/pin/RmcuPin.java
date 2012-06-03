package net.awired.restmcu.api.domain.pin;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "pin")
@XmlAccessorType(XmlAccessType.FIELD)
public class RmcuPin {

    private String name; // updatable
    private String description;
    private RmcuPinDirection direction;
    private RmcuPinType type;
    private Float valueMin;
    private Float valueMax;

    private List<RmcuPinNotify> notifies; // input only, updatable

    public void addNotify(RmcuPinNotify notify) {
        if (this.notifies == null) {
            this.notifies = new ArrayList<RmcuPinNotify>();
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

    public List<RmcuPinNotify> getNotifies() {
        return notifies;
    }

    public void setNotifies(List<RmcuPinNotify> notifies) {
        this.notifies = notifies;
    }

    public RmcuPinDirection getDirection() {
        return direction;
    }

    public RmcuPinType getType() {
        return type;
    }

    public void setType(RmcuPinType type) {
        this.type = type;
    }

    public void setDirection(RmcuPinDirection direction) {
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
