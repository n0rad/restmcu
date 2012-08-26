package net.awired.restmcu.it;

import static org.junit.Assert.assertFalse;
import net.awired.restmcu.api.resource.client.RestMcuPinResource;
import net.awired.restmcu.it.RestMcuTestRule;
import org.junit.Rule;
import org.junit.Test;

public class DoubleLedSwitcherIT {

    @Rule
    public RestMcuTestRule restmcu = new RestMcuTestRule();

    class BasicThread1 extends Thread {

        private final int pin;
        private final float lowValue;
        private final float highValue;
        public boolean failure;

        public BasicThread1(int pin, float lowValue, float highValue) {
            this.pin = pin;
            this.lowValue = lowValue;
            this.highValue = highValue;

        }

        @Override
        public void run() {
            RestMcuPinResource pinResource = restmcu.getPinResource();
            for (int i = 0; i < 100; i++) {
                try {
                    pinResource.setPinValue(pin, highValue);
                    if (pinResource.getPinValue(pin) != highValue) {
                        failure = true;
                        break;
                    }
                    pinResource.setPinValue(pin, lowValue);
                    if (pinResource.getPinValue(pin) != lowValue) {
                        failure = true;
                        break;
                    }
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }

        }
    }

    @Test
    public void should_toggle_red_led() throws Exception {
        BasicThread1 thread1 = new BasicThread1(7, 0f, 1f);
        BasicThread1 thread2 = new BasicThread1(6, 0f, 255f);

        thread1.start();
        thread2.start();

        thread1.join();
        thread2.join();

        assertFalse(thread1.failure);
        assertFalse(thread2.failure);
    }
}
