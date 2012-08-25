package net.awired.restmcu.it;

import static org.junit.Assert.assertEquals;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import net.awired.restmcu.api.domain.pin.RestMcuPinNotify;
import net.awired.restmcu.api.domain.pin.RestMcuPinNotifyCondition;
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
        List<RestMcuPinNotify> asList = Arrays.asList(
                new RestMcuPinNotify(RestMcuPinNotifyCondition.INF_OR_EQUAL, 1f), new RestMcuPinNotify(
                        RestMcuPinNotifyCondition.SUP_OR_EQUAL, 1f));
        value.setNotifies(asList);

        restmcu.getPinResource().setPinSettings(8, value);

        List<RestMcuPinNotify> notifies = restmcu.getPinResource().getPinSettings(8).getNotifies();
        assertEquals(2, notifies.size());
        assertEquals(asList.get(0), notifies.get(0));
        assertEquals(asList.get(1), notifies.get(1));
    }

    @Test
    public void should_update_notify2() throws Exception {
        RestMcuPinSettings value = new RestMcuPinSettings();
        List<RestMcuPinNotify> asList = Arrays.asList(
                new RestMcuPinNotify(RestMcuPinNotifyCondition.INF_OR_EQUAL, 0f), new RestMcuPinNotify(
                        RestMcuPinNotifyCondition.SUP_OR_EQUAL, 0f));
        value.setNotifies(asList);

        restmcu.getPinResource().setPinSettings(8, value);

        List<RestMcuPinNotify> notifies = restmcu.getPinResource().getPinSettings(8).getNotifies();
        assertEquals(2, notifies.size());
        assertEquals(asList.get(0), notifies.get(0));
        assertEquals(asList.get(1), notifies.get(1));
    }

    @Test
    public void should_update_notify3() throws Exception {
        RestMcuPinSettings value = new RestMcuPinSettings();
        // its duplicated but should not be a problem for the controller as it will notify only once
        List<RestMcuPinNotify> asList = Arrays.asList(
                new RestMcuPinNotify(RestMcuPinNotifyCondition.INF_OR_EQUAL, 0f), //
                new RestMcuPinNotify(RestMcuPinNotifyCondition.SUP_OR_EQUAL, 0f), // 
                new RestMcuPinNotify(RestMcuPinNotifyCondition.INF_OR_EQUAL, 0f), // 
                new RestMcuPinNotify(RestMcuPinNotifyCondition.SUP_OR_EQUAL, 0f));
        value.setNotifies(asList);

        restmcu.getPinResource().setPinSettings(8, value);

        List<RestMcuPinNotify> notifies = restmcu.getPinResource().getPinSettings(8).getNotifies();
        assertEquals(4, notifies.size());
        assertEquals(asList.get(0), notifies.get(0));
        assertEquals(asList.get(1), notifies.get(1));
        assertEquals(asList.get(2), notifies.get(2));
        assertEquals(asList.get(3), notifies.get(3));
    }

    @Test
    public void should_flush_notifies() throws Exception {
        RestMcuPinSettings value = new RestMcuPinSettings();
        value.setNotifies(new ArrayList<RestMcuPinNotify>());

        restmcu.getPinResource().setPinSettings(8, value);

        List<RestMcuPinNotify> notifies = restmcu.getPinResource().getPinSettings(8).getNotifies();
        assertEquals(0, notifies.size());
    }

}
