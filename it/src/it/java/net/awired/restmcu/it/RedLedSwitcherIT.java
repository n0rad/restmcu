package net.awired.restmcu.it;

import static org.junit.Assert.assertEquals;
import net.awired.restmcu.api.resource.client.RestMcuPinResource;
import net.awired.restmcu.it.RestMcuTestRule;
import org.junit.Rule;
import org.junit.Test;

public class RedLedSwitcherIT {

    @Rule
    public RestMcuTestRule restmcu = new RestMcuTestRule();

    @Test
    public void should_toggle_red_led_fastest() throws Exception {
        RestMcuPinResource pinResource = restmcu.getPinResource();
        for (int i = 0; i < 100; i++) {
            pinResource.setPinValue(7, 1f);
            pinResource.setPinValue(7, 0f);
        }
    }

    @Test
    public void should_toggle_red_led() throws Exception {
        RestMcuPinResource pinResource = restmcu.getPinResource();
        for (int i = 0; i < 100; i++) {
            pinResource.setPinValue(7, 1f);
            assertEquals(1f, pinResource.getPinValue(7), 0);
            pinResource.setPinValue(7, 0f);
            assertEquals(0f, pinResource.getPinValue(7), 0);
        }
    }
}
