package net.awired.restmcu.api.domain.board;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "pinNotification")
@XmlAccessorType(XmlAccessType.FIELD)
public class RestMcuBoardNotification {

    private RestMcuBoardNotificationType type;

    public void setType(RestMcuBoardNotificationType type) {
        this.type = type;
    }

    public RestMcuBoardNotificationType getType() {
        return type;
    }
}
