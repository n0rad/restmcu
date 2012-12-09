package net.awired.restmcu.it;

import static org.junit.Assert.assertEquals;
import net.awired.restmcu.api.resource.client.RestMcuLineResource;
import net.awired.restmcu.it.RestmcuTestRule;
import org.junit.Rule;
import org.junit.Test;

public class RedLedSwitcherIT {

    @Rule
    public RestmcuTestRule restmcu = new RestmcuTestRule();

    @Test
    public void should_toggle_red_led_fastest() throws Exception {
        RestMcuLineResource lineResource = restmcu.getLineResource();
        for (int i = 0; i < 100; i++) {
            lineResource.setLineValue(7, 1f);
            lineResource.setLineValue(7, 0f);
        }
    }

    @Test
    public void should_toggle_red_led() throws Exception {
        RestMcuLineResource lineResource = restmcu.getLineResource();
        for (int i = 0; i < 100; i++) {
            lineResource.setLineValue(7, 1f);
            assertEquals(1f, lineResource.getLineValue(7), 0);
            lineResource.setLineValue(7, 0f);
            assertEquals(0f, lineResource.getLineValue(7), 0);
        }
    }
}
