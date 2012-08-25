package net.awired.restmcu.it;

import static net.awired.restmcu.api.domain.pin.RestMcuPinNotifyCondition.inf_or_equal;
import static net.awired.restmcu.api.domain.pin.RestMcuPinNotifyCondition.sup_or_equal;
import static org.junit.Assert.assertEquals;
import java.util.Arrays;
import net.awired.restmcu.api.domain.pin.RestMcuPinNotify;
import net.awired.restmcu.api.domain.pin.RestMcuPinSettings;
import org.junit.Rule;
import org.junit.Test;

public class PinResourceIT {

    @Rule
    public RestMcuTestRule restmcu = new RestMcuTestRule();

    @Test
    public void should_change_name() throws Exception {
        RestMcuPinSettings value = new RestMcuPinSettings();
        value.setName("myname");

        restmcu.getPinResource().setPinSettings(6, value);

        assertEquals("myname", restmcu.getPinResource().getPinSettings(6).getName());
    }

    @Test
    public void should_update_notify() throws Exception {
        RestMcuPinSettings value = new RestMcuPinSettings();
        value.setNotifies(Arrays.asList(new RestMcuPinNotify(inf_or_equal, 0f),
                new RestMcuPinNotify(sup_or_equal, 1f)));

        restmcu.getPinResource().setPinSettings(8, value);

        assertEquals("myname", restmcu.getPinResource().getPinSettings(6).getName());
    }

}
