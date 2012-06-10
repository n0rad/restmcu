package net.awired.restmcu.stub;

import net.awired.restmcu.api.resource.test.RestMcuDebugResource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RmcuDebugResourceImpl implements RestMcuDebugResource {

    @Autowired
    private HccContext context;

    @Override
    public void setDebugValue(int pinId, Float value) {
        //        context.setdebugValue(pinId, value);
    }

}
