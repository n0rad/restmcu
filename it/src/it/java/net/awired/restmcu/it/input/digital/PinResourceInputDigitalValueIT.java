package net.awired.restmcu.it.input.digital;

import net.awired.restmcu.it.RestMcuTestRule;
import org.junit.Rule;

public class PinResourceInputDigitalValueIT {

    private static final int PIN_ID = 5;

    @Rule
    public RestMcuTestRule hcc = new RestMcuTestRule();
    //
    //    @Test
    //    public void should_get_value() throws Exception {
    //        assertEquals((Float) 1f, hcc.getPinResource().getValue(PIN_ID));
    //    }
    //
    //    @Test(expected = HccUpdateException.class)
    //    public void should_not_set_value() throws Exception {
    //        hcc.getPinResource().setValue(PIN_ID, 0f);
    //    }

}
