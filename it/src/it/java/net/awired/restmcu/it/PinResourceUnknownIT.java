package net.awired.restmcu.it;

import net.awired.ajsl.core.lang.exception.NotFoundException;
import net.awired.restmcu.api.domain.pin.RestMcuPinSettings;
import org.junit.Rule;
import org.junit.Test;

public class PinResourceUnknownIT {

    @Rule
    public RestMcuTestRule restmcu = new RestMcuTestRule();

    @Test(expected = NotFoundException.class)
    public void should_throw_exception_on_unknown_pin() throws Exception {
        restmcu.getPinResource().getPin(42);
    }

    @Test(expected = NotFoundException.class)
    public void should_throw_exception_on_unknown_pin2() throws Exception {
        restmcu.getPinResource().getPin(-1);
    }

    @Test(expected = NotFoundException.class)
    public void should_throw_exception_on_unknown_pin3() throws Exception {
        restmcu.getPinResource().getPin(42);
    }

    @Test(expected = NotFoundException.class)
    public void should_throw_exception_on_unknown_pin4() throws Exception {
        restmcu.getPinResource().getPin(-1);
    }

    @Test(expected = NotFoundException.class)
    public void should_throw_exception_on_unknown_pin5() throws Exception {
        restmcu.getPinResource().getPinValue(42);
    }

    @Test(expected = NotFoundException.class)
    public void should_throw_exception_on_unknown_pin6() throws Exception {
        restmcu.getPinResource().getPinValue(-1);
    }

    @Test(expected = NotFoundException.class)
    public void should_throw_exception_on_unknown_pin13() throws Exception {
        restmcu.getPinResource().setPinSettings(42, new RestMcuPinSettings());
    }

    @Test(expected = NotFoundException.class)
    public void should_throw_exception_on_unknown_pin14() throws Exception {
        restmcu.getPinResource().setPinSettings(-1, new RestMcuPinSettings());
    }

    @Test(expected = NotFoundException.class)
    public void should_throw_exception_on_unknown_pin15() throws Exception {
        restmcu.getPinResource().setPinValue(42, 0f);
    }

    @Test(expected = NotFoundException.class)
    public void should_throw_exception_on_unknown_pin16() throws Exception {
        restmcu.getPinResource().setPinValue(-1, 0f);
    }

}
