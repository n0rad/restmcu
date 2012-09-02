package net.awired.restmcu.it;

import static org.junit.Assert.assertEquals;
import net.awired.restmcu.api.resource.client.RestMcuPinResource;
import org.junit.Rule;
import org.junit.Test;

public class RedLedSwitcherSecuredIT {

    @Rule
    public RestMcuTestRule restmcu = new RestMcuTestRule();

    // sha256      : 30s
    // sha1        : 18s
    // sha1 both   : 24s
    // no security : 10s
    @Test
    public void should_toggle_red_led_fastest() throws Exception {
        RestMcuPinResource pinResource = restmcu.getPinResource();
        for (int i = 0; i < 100; i++) {
            pinResource.setPinValue(7, 1f);
            assertEquals(1f, pinResource.getPinValue(7), 0);
            pinResource.setPinValue(7, 0f);
            assertEquals(0f, pinResource.getPinValue(7), 0);
        }
    }

    @Test
    public void should_toggle_red_led_fastest2() throws Exception {
        restmcu.getBoardResource().getBoardSettings();
        RestMcuPinResource pinResource = restmcu.getPinResource();
        pinResource.setPinValue(7, 1f);
        //            assertEquals(1f, pinResource.getPinValue(7), 0);
        pinResource.setPinValue(7, 0f);
        //            assertEquals(0f, pinResource.getPinValue(7), 0);
    }
}
