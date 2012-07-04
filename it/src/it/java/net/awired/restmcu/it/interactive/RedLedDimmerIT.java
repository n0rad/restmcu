package net.awired.restmcu.it.interactive;

import net.awired.restmcu.api.resource.client.RestMcuPinResource;
import net.awired.restmcu.it.RestMcuTestRule;
import org.junit.Rule;
import org.junit.Test;

public class RedLedDimmerIT {

    @Rule
    public RestMcuTestRule hcc = new RestMcuTestRule();

    @Test
    public void should_dim_red_led_up_and_down() {
        RestMcuPinResource pinResource = hcc.getPinResource();

        //        for (int i = 0; i < 255; i++) {
        //            pinResource.setPinValue(pinId, value);
        //        }
    }
}
