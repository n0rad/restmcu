package net.awired.restmcu.it.interactive;

import net.awired.restmcu.api.resource.client.RestMcuPinResource;
import net.awired.restmcu.it.Interactive;
import net.awired.restmcu.it.RestMcuTestRule;
import org.junit.Rule;
import org.junit.Test;

@Interactive
public class PushButtonIT {

    @Rule
    public RestMcuTestRule hcc = new RestMcuTestRule();

    @Test
    public void should_push_the_button() throws Exception {
        RestMcuPinResource pinResource = hcc.getPinResource();

        while (true) {
            Thread.sleep(500);
            System.out.println("Please press the push button");
            Float pinValue = pinResource.getPinValue(8);
            if (pinValue == 1) {
                break;
            }
        }

    }

}
