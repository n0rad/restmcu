package net.awired.restmcu.it;

import static org.junit.Assert.assertEquals;
import net.awired.restmcu.api.resource.client.RestMcuLineResource;
import net.awired.restmcu.it.RestMcuTestRule;
import org.junit.Rule;
import org.junit.Test;

public class GreenLedDimmerIT {

    @Rule
    public RestMcuTestRule restmcu = new RestMcuTestRule();

    @Test
    public void should_dim_green_led_up_and_down() throws Exception {
        RestMcuLineResource lineResource = restmcu.getLineResource();

        for (int i = 0; i <= 255; i++) {
            lineResource.setLineValue(6, (float) i);
            assertEquals(i, lineResource.getLineValue(6), 0);
        }
        for (int i = 255; i >= 0; i--) {
            lineResource.setLineValue(6, (float) i);
            assertEquals(i, lineResource.getLineValue(6), 0);
        }
    }
}
