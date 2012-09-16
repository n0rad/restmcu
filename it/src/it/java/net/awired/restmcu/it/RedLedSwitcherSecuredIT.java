package net.awired.restmcu.it;

import static org.junit.Assert.assertEquals;
import net.awired.restmcu.api.resource.client.RestMcuLineResource;
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
        RestMcuLineResource lineResource = restmcu.getLineResource();
        for (int i = 0; i < 100; i++) {
            lineResource.setLineValue(7, 1f);
            assertEquals(1f, lineResource.getLineValue(7), 0);
            lineResource.setLineValue(7, 0f);
            assertEquals(0f, lineResource.getLineValue(7), 0);
        }
    }

    @Test
    public void should_toggle_red_led_fastest2() throws Exception {
        RestMcuLineResource lineResource = restmcu.getLineResource();
        //        lineResource.getLineSettings(7);
        lineResource.setLineValue(7, 1f);
        //            assertEquals(1f, lineResource.getLineValue(7), 0);
        //        lineResource.setLineValue(7, 0f);
        //            assertEquals(0f, lineResource.getLineValue(7), 0);
    }
}
