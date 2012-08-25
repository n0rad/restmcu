package net.awired.restmcu.api.domain.pin;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "pin")
@XmlAccessorType(XmlAccessType.FIELD)
public class RestMcuPin {

    private String description;
    private RestMcuPinDirection direction;
    private RestMcuPinType type;
    private Float valueMin;
    private Float valueMax;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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
