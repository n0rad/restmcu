package net.awired.restmcu.stub;

import net.awired.ajsl.core.lang.exception.NotFoundException;
import net.awired.ajsl.core.lang.exception.UpdateException;
import net.awired.restmcu.api.domain.pin.RmcuPin;
import net.awired.restmcu.api.resource.client.RmcuPinResource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PinResourceStub implements RmcuPinResource {

    @Autowired
    HccContext hccContext;

    @Override
    public RmcuPin getPin(int pinId) throws NotFoundException {
        return hccContext.getPin(pinId);
    }

    @Override
    public void setPin(int pinId, RmcuPin pin) throws NotFoundException, UpdateException {
        //        hccContext.updatePin(pinId, pin);
    }

    @Override
    public Float getPinValue(int pinId) throws NotFoundException {
        //        return hccContext.getPin(pinId).getValue();
        return null;
    }

    @Override
    public void setPinValue(int pinId, Float value) throws NotFoundException, UpdateException {
        //        hccContext.setPinValue(pinId, value);
    }

}
