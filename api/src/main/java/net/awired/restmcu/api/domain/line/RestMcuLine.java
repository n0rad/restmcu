package net.awired.restmcu.api.domain.line;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import com.google.common.base.Objects;

@XmlRootElement(name = "line")
@XmlAccessorType(XmlAccessType.FIELD)
public class RestMcuLine {

    private int id;
    private String description;
    private RestMcuLineDirection direction;
    private RestMcuLineType type;
    private Float valueMin;
    private Float valueMax;
    private Float value;

    @Override
    public String toString() {
        return Objects.toStringHelper(this) //
                .add("id", id) //
                .add("description", description) //
                .add("direction", direction) //
                .add("type", type) //
                .add("valueMin", valueMin) //
                .add("valueMax", valueMax) //
                .add("value", value) //
                .toString();
    }

    ///////////////////////////:

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public RestMcuLineDirection getDirection() {
        return direction;
    }

    public RestMcuLineType getType() {
        return type;
    }

    public void setType(RestMcuLineType type) {
        this.type = type;
    }

    public void setDirection(RestMcuLineDirection direction) {
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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Float getValue() {
        return value;
    }

    public void setValue(Float value) {
        this.value = value;
    }

}
