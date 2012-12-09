package net.awired.restmcu.it;

import static org.junit.Assert.assertFalse;
import net.awired.restmcu.api.resource.client.RestMcuLineResource;
import net.awired.restmcu.it.RestmcuTestRule;
import org.junit.Rule;
import org.junit.Test;

public class DoubleLedSwitcherIT {

    @Rule
    public RestmcuTestRule restmcu = new RestmcuTestRule();

    class BasicThread1 extends Thread {

        private final int line;
        private final float lowValue;
        private final float highValue;
        public boolean failure;

        public BasicThread1(int line, float lowValue, float highValue) {
            this.line = line;
            this.lowValue = lowValue;
            this.highValue = highValue;

        }

        @Override
        public void run() {
            RestMcuLineResource lineResource = restmcu.getLineResource();
            for (int i = 0; i < 100; i++) {
                try {
                    lineResource.setLineValue(line, highValue);
                    if (lineResource.getLineValue(line) != highValue) {
                        failure = true;
                        break;
                    }
                    lineResource.setLineValue(line, lowValue);
                    if (lineResource.getLineValue(line) != lowValue) {
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
