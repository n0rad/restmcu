package net.awired.restmcu.it.interactive;

import static org.junit.Assert.assertTrue;
import net.awired.aclm.ask.Ask;
import net.awired.aclm.param.CliParamBoolean;
import net.awired.restmcu.api.resource.client.RestMcuPinResource;
import net.awired.restmcu.it.Interactive;
import net.awired.restmcu.it.RestMcuTestRule;
import org.junit.Rule;
import org.junit.Test;

@Interactive
public class GreenLedDimmerIT {

    @Rule
    public RestMcuTestRule hcc = new RestMcuTestRule();

    @Test
    public void should_dim_red_led_up_and_down() throws Exception {
        RestMcuPinResource pinResource = hcc.getPinResource();

        for (int i = 0; i <= 255; i++) {
            pinResource.setPinValue(6, (float) i);
        }
        for (int i = 255; i >= 0; i--) {
            pinResource.setPinValue(6, (float) i);
        }
        assertTrue(
                "not seen",
                Ask.run("did you see the green led dimming up then down in about 13sec",
                        new CliParamBoolean("seen").setParamDescription("Y/N")));

    }
}
