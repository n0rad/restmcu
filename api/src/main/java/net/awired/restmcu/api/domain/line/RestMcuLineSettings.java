package net.awired.restmcu.api.domain.line;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "lineSettings")
@XmlAccessorType(XmlAccessType.FIELD)
public class RestMcuLineSettings {

    private String name;
    private List<RestMcuLineNotify> notifies;

    public void addNotify(RestMcuLineNotify notify) {
        if (this.notifies == null) {
            this.notifies = new ArrayList<RestMcuLineNotify>();
        }
        this.notifies.add(notify);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<RestMcuLineNotify> getNotifies() {
        return notifies;
    }

    public void setNotifies(List<RestMcuLineNotify> notifies) {
        this.notifies = notifies;
    }

}
