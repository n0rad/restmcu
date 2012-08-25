package net.awired.restmcu.it.interactive;

import net.awired.restmcu.api.resource.client.RestMcuPinResource;
import net.awired.restmcu.it.Interactive;
import net.awired.restmcu.it.RestMcuTestRule;
import org.junit.Rule;
import org.junit.Test;

@Interactive
public class RedLedSwitcherIT {

    @Rule
    public RestMcuTestRule hcc = new RestMcuTestRule();

    @Test
    public void should_toggle_red_led() throws Exception {
        RestMcuPinResource pinResource = hcc.getPinResource();
        for (int i = 0; i < 100; i++) {
            pinResource.setPinValue(7, 1f);
            pinResource.setPinValue(7, 0f);
        }

        //        assertTrue(
        //                "not seen",
        //                Ask.run("did you see the red led blinking verify fast",
        //                        new CliParamBoolean("seen").setParamDescription("Y/N")));
    }
}
