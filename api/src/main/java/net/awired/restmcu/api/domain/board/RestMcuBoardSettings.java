package net.awired.restmcu.api.domain.board;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "boardSettings")
public class RestMcuBoardSettings {

    private String name;
    private String notifyUrl;
    private String ip;
    private Integer port;

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public Integer getPort() {
        return port;
    }

    public void setPort(Integer port) {
        this.port = port;
    }

    public String getNotifyUrl() {
        return notifyUrl;
    }

    public void setNotifyUrl(String notifyUrl) {
        this.notifyUrl = notifyUrl;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
