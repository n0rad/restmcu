package net.awired.restmcu.it;

import net.awired.restmcu.api.filter.RestMcuSecurityKey;
import org.apache.cxf.message.Message;

public class RestMcuTestSecurityKey implements RestMcuSecurityKey {

    private static final String key = "TOTO42";

    @Override
    public String getKey() {
        return key;
    }

    @Override
    public String buildMessage(Message message) {
        return "MESSAGE";
    }

}
