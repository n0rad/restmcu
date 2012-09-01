package net.awired.restmcu.api.filter;

import org.apache.cxf.message.Message;

public interface RestMcuSecurityKey {

    String getKey();

    String buildMessage(long posixTimestamp, Message message);

}
