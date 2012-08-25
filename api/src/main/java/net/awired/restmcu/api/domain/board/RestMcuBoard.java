package net.awired.restmcu.api.domain.board;

import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "board")
@XmlAccessorType(XmlAccessType.FIELD)
public class RestMcuBoard {

    private String description;
    private String software;
    private String version;
    private String hardware;
    private String mac;
    private List<Integer> pinIds;
    private Integer freeMemory;

    public String getSoftware() {
        return software;
    }

    public void setSoftware(String software) {
        this.software = software;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getMac() {
        return mac;
    }

    public void setMac(String mac) {
        this.mac = mac;
    }

    public void setHardware(String hardware) {
        this.hardware = hardware;
    }

    public String getHardware() {
        return hardware;
    }

    public void setPinIds(List<Integer> pinIds) {
        this.pinIds = pinIds;
    }

    public List<Integer> getPinIds() {
        return pinIds;
    }

    public Integer getFreeMemory() {
        return freeMemory;
    }

    public void setFreeMemory(Integer freeMemory) {
        this.freeMemory = freeMemory;
    }

}
