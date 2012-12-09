package net.awired.restmcu.it;

import static org.junit.Assert.assertEquals;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import net.awired.restmcu.api.domain.line.RestMcuLineNotify;
import net.awired.restmcu.api.domain.line.RestMcuLineNotifyCondition;
import net.awired.restmcu.api.domain.line.RestMcuLineSettings;
import org.junit.Rule;
import org.junit.Test;

public class LineResourceIT {

    @Rule
    public RestmcuTestRule restmcu = new RestmcuTestRule();

    @Test
    public void should_change_name() throws Exception {
        RestMcuLineSettings value = new RestMcuLineSettings();
        value.setName("myname");

        restmcu.getLineResource().setLineSettings(6, value);

        assertEquals("myname", restmcu.getLineResource().getLineSettings(6).getName());
    }

    @Test
    public void should_update_notify() throws Exception {
        RestMcuLineSettings value = new RestMcuLineSettings();
        List<RestMcuLineNotify> asList = Arrays.asList(
                new RestMcuLineNotify(RestMcuLineNotifyCondition.INF_OR_EQUAL, 1f), new RestMcuLineNotify(
                        RestMcuLineNotifyCondition.SUP_OR_EQUAL, 1f));
        value.setNotifies(asList);

        restmcu.getLineResource().setLineSettings(8, value);

        List<RestMcuLineNotify> notifies = restmcu.getLineResource().getLineSettings(8).getNotifies();
        assertEquals(2, notifies.size());
        assertEquals(asList.get(0), notifies.get(0));
        assertEquals(asList.get(1), notifies.get(1));
    }

    @Test
    public void should_update_notify2() throws Exception {
        RestMcuLineSettings value = new RestMcuLineSettings();
        List<RestMcuLineNotify> asList = Arrays.asList(
                new RestMcuLineNotify(RestMcuLineNotifyCondition.INF_OR_EQUAL, 0f), new RestMcuLineNotify(
                        RestMcuLineNotifyCondition.SUP_OR_EQUAL, 0f));
        value.setNotifies(asList);

        restmcu.getLineResource().setLineSettings(8, value);

        List<RestMcuLineNotify> notifies = restmcu.getLineResource().getLineSettings(8).getNotifies();
        assertEquals(2, notifies.size());
        assertEquals(asList.get(0), notifies.get(0));
        assertEquals(asList.get(1), notifies.get(1));
    }

    @Test
    public void should_update_notify3() throws Exception {
        RestMcuLineSettings value = new RestMcuLineSettings();
        // its duplicated but should not be a problem for the controller as it will notify only once
        List<RestMcuLineNotify> asList = Arrays.asList(
                new RestMcuLineNotify(RestMcuLineNotifyCondition.INF_OR_EQUAL, 0f), //
                new RestMcuLineNotify(RestMcuLineNotifyCondition.SUP_OR_EQUAL, 0f), // 
                new RestMcuLineNotify(RestMcuLineNotifyCondition.INF_OR_EQUAL, 0f), // 
                new RestMcuLineNotify(RestMcuLineNotifyCondition.SUP_OR_EQUAL, 0f));
        value.setNotifies(asList);

        restmcu.getLineResource().setLineSettings(8, value);

        List<RestMcuLineNotify> notifies = restmcu.getLineResource().getLineSettings(8).getNotifies();
        assertEquals(4, notifies.size());
        assertEquals(asList.get(0), notifies.get(0));
        assertEquals(asList.get(1), notifies.get(1));
        assertEquals(asList.get(2), notifies.get(2));
        assertEquals(asList.get(3), notifies.get(3));
    }

    @Test
    public void should_flush_notifies() throws Exception {
        RestMcuLineSettings value = new RestMcuLineSettings();
        value.setNotifies(new ArrayList<RestMcuLineNotify>());

        restmcu.getLineResource().setLineSettings(8, value);

        List<RestMcuLineNotify> notifies = restmcu.getLineResource().getLineSettings(8).getNotifies();
        assertEquals(0, notifies.size());
    }

}
