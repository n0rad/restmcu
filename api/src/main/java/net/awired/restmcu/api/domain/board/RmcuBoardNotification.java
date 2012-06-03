package net.awired.restmcu.api.domain.board;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "pinNotification")
@XmlAccessorType(XmlAccessType.FIELD)
public class RmcuBoardNotification {

    private RmcuBoardNotificationType type;

    public void setType(RmcuBoardNotificationType type) {
        this.type = type;
    }

    public RmcuBoardNotificationType getType() {
        return type;
    }
}
