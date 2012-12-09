package net.awired.restmcu.it;

import net.awired.restmcu.api.filter.RestMcuSecurityKey;
import org.apache.cxf.message.Message;

public class RestmcuTestSecurityKey implements RestMcuSecurityKey {

    private static final String key = "TOTO42";

    @Override
    public String getKey() {
        return key;
    }

    @Override
    public String buildMessage(long posixTimestamp, Message message) {
        return posixTimestamp + "MESSAGE";
    }

}
