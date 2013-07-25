package org.housecream.restmcu.it;

import static org.junit.Assert.assertEquals;
import org.housecream.restmcu.api.resource.client.RestMcuLineResource;
import org.housecream.restmcu.it.RestmcuTestRule;
import org.junit.Rule;
import org.junit.Test;

public class GreenLedDimmerIT {

    @Rule
    public RestmcuTestRule restmcu = new RestmcuTestRule();

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
