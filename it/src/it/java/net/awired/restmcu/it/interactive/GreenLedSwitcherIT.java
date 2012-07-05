package net.awired.restmcu.it.interactive;

import net.awired.restmcu.it.RestMcuTestRule;
import org.junit.Rule;
import org.junit.Test;

public class GreenLedSwitcherIT {

    @Rule
    public RestMcuTestRule hcc = new RestMcuTestRule();

    @Test
    public void should_toggle_green_led() throws Exception {
        for (int i = 0; i < 100; i++) {
            hcc.getPinResource().setPinValue(3, 1f);
            hcc.getPinResource().setPinValue(3, 0f);
        }
    }

}
