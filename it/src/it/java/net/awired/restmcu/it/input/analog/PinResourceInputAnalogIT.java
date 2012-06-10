package net.awired.restmcu.it.input.analog;

import net.awired.restmcu.it.RestMcuTestRule;
import org.junit.Rule;

public class PinResourceInputAnalogIT {

    private static final int PIN_ID = 2;

    @Rule
    public RestMcuTestRule hcc = new RestMcuTestRule();

    //    @Test
    //    public void should_get_valid_input_pin() throws Exception {
    //
    //        HccPinDescription pin = hcc.getPinResource().getPinDescription(PIN_ID);
    //
    //        assertTrue(EqualsBuilder.reflectionEquals(pin, DefaultITDomainHelper.buildDefaultPin(PIN_ID)
    //                .getDescription()));
    //    }

}
