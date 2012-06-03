package net.awired.restmcu.it.input.analog;

import net.awired.restmcu.it.RmcuTestRule;
import org.junit.Rule;

public class PinResourceInputAnalogValueIT {

    private static final int PIN_ID = 2;

    @Rule
    public RmcuTestRule hcc = new RmcuTestRule();

    //    @Test
    //    public void should_get_value() throws Exception {
    //        assertEquals((Float) 952f, hcc.getPinResource().getValue(PIN_ID));
    //    }
    //
    //    @Test(expected = HccUpdateException.class)
    //    public void should_not_set_value() throws Exception {
    //        hcc.getPinResource().setValue(PIN_ID, 0f);
    //    }

}
