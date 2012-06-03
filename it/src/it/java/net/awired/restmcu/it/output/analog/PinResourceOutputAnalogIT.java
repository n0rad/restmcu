package net.awired.restmcu.it.output.analog;

import net.awired.restmcu.it.RmcuTestRule;
import org.junit.Rule;

public class PinResourceOutputAnalogIT {

    private static final int PIN_ID = 3;

    @Rule
    public RmcuTestRule hcc = new RmcuTestRule();

    //    @Test
    //    public void should_get_valid_output_pin() throws Exception {
    //
    //        HccPinDescription pin = hcc.getPinResource().getPinDescription(PIN_ID);
    //
    //        assertTrue(EqualsBuilder.reflectionEquals(pin, DefaultITDomainHelper.buildDefaultPin(PIN_ID)
    //                .getDescription()));
    //    }
}
