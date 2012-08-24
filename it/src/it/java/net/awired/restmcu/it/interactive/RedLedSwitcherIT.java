package net.awired.restmcu.it.interactive;

import static org.junit.Assert.assertTrue;
import net.awired.aclm.ask.Ask;
import net.awired.aclm.param.CliParamBoolean;
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
        for (int i = 0; i < 100; i++) {
            hcc.getPinResource().setPinValue(7, 1f);
            hcc.getPinResource().setPinValue(7, 0f);
        }

        assertTrue(
                "not seen",
                Ask.run("did you see the red led blinking verify fast",
                        new CliParamBoolean("seen").setParamDescription("Y/N")));
    }
}
