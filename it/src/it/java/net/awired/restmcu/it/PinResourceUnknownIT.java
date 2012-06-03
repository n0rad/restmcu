package net.awired.restmcu.it;

import net.awired.ajsl.core.lang.exception.NotFoundException;
import net.awired.restmcu.api.domain.pin.RmcuPin;
import org.junit.Ignore;
import org.junit.Rule;
import org.junit.Test;

@Ignore
public class PinResourceUnknownIT {

    @Rule
    public RmcuTestRule hcc = new RmcuTestRule();

    @Test(expected = NotFoundException.class)
    public void should_throw_exception_on_unknown_pin() throws Exception {
        hcc.getPinResource().getPin(42);
    }

    @Test(expected = NotFoundException.class)
    public void should_throw_exception_on_unknown_pin2() throws Exception {
        hcc.getPinResource().getPin(-1);
    }

    @Test(expected = NotFoundException.class)
    public void should_throw_exception_on_unknown_pin3() throws Exception {
        hcc.getPinResource().getPin(42);
    }

    @Test(expected = NotFoundException.class)
    public void should_throw_exception_on_unknown_pin4() throws Exception {
        hcc.getPinResource().getPin(-1);
    }

    @Test(expected = NotFoundException.class)
    public void should_throw_exception_on_unknown_pin5() throws Exception {
        hcc.getPinResource().getPinValue(42);
    }

    @Test(expected = NotFoundException.class)
    public void should_throw_exception_on_unknown_pin6() throws Exception {
        hcc.getPinResource().getPinValue(-1);
    }

    @Test(expected = NotFoundException.class)
    public void should_throw_exception_on_unknown_pin13() throws Exception {
        hcc.getPinResource().setPin(42, new RmcuPin());
    }

    @Test(expected = NotFoundException.class)
    public void should_throw_exception_on_unknown_pin14() throws Exception {
        hcc.getPinResource().setPin(-1, new RmcuPin());
    }

    @Test(expected = NotFoundException.class)
    public void should_throw_exception_on_unknown_pin15() throws Exception {
        hcc.getPinResource().setPinValue(42, 0f);
    }

    @Test(expected = NotFoundException.class)
    public void should_throw_exception_on_unknown_pin16() throws Exception {
        hcc.getPinResource().setPinValue(-1, 0f);
    }

}
