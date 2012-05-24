package net.awired.restmcu.api.domain.board;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "pinNotification")
@XmlAccessorType(XmlAccessType.FIELD)
public class HccBoardNotification {

    private HccBoardNotificationType type;

    public void setType(HccBoardNotificationType type) {
        this.type = type;
    }

    public HccBoardNotificationType getType() {
        return type;
    }
}
