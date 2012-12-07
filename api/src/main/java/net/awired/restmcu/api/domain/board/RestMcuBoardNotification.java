package net.awired.restmcu.api.domain.board;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import com.google.common.base.Objects;

@XmlRootElement(name = "lineNotification")
@XmlAccessorType(XmlAccessType.FIELD)
public class RestMcuBoardNotification {

    private RestMcuBoardNotificationType type;

    public RestMcuBoardNotification() {
    }

    public RestMcuBoardNotification(RestMcuBoardNotificationType type) {
        this.type = type;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((type == null) ? 0 : type.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        RestMcuBoardNotification other = (RestMcuBoardNotification) obj;
        if (type != other.type) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return Objects.toStringHelper(this) //
                .add("type", type) //
                .toString();
    }

    ///////////////////////////////////////////////////

    public void setType(RestMcuBoardNotificationType type) {
        this.type = type;
    }

    public RestMcuBoardNotificationType getType() {
        return type;
    }

}
