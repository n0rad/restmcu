package net.awired.restmcu.stub;

import net.awired.restmcu.api.resource.test.RmcuDebugResource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RmcuDebugResourceImpl implements RmcuDebugResource {

    @Autowired
    private HccContext context;

    @Override
    public void setDebugValue(int pinId, Float value) {
        //        context.setdebugValue(pinId, value);
    }

}
