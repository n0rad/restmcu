package net.awired.restmcu.api.domain.pin;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "pinSettings")
@XmlAccessorType(XmlAccessType.FIELD)
public class RestMcuPinSettings {

    private String name;
    private List<RestMcuPinNotify> notifies;

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

    public List<RestMcuPinNotify> getNotifies() {
        return notifies;
    }

    public void setNotifies(List<RestMcuPinNotify> notifies) {
        this.notifies = notifies;
    }

}
