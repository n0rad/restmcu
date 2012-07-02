package net.awired.restmcu.api.domain.board;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import com.google.common.base.Objects;

@XmlRootElement(name = "pinNotification")
@XmlAccessorType(XmlAccessType.FIELD)
public class RestMcuBoardNotification {

    private RestMcuBoardNotificationType type;

    @Override
    public String toString() {
        return Objects.toStringHelper(this) //
                .add("type", type) //
                .toString();
    }

    public void setType(RestMcuBoardNotificationType type) {
        this.type = type;
    }

    public RestMcuBoardNotificationType getType() {
        return type;
    }
}
