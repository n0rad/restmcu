package org.housecream.restmcu.it;

import static org.junit.Assert.assertEquals;
import java.util.Arrays;
import org.housecream.restmcu.api.domain.board.RestMcuBoardSettings;
import org.housecream.restmcu.api.domain.line.RestMcuLineNotification;
import org.housecream.restmcu.api.domain.line.RestMcuLineNotify;
import org.housecream.restmcu.api.domain.line.RestMcuLineNotifyCondition;
import org.housecream.restmcu.api.domain.line.RestMcuLineSettings;
import org.housecream.restmcu.api.resource.client.RestMcuLineResource;
import org.housecream.restmcu.it.resource.NotifyResource;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import fr.norad.core.io.NetworkUtils;
import fr.norad.jaxrs.junit.RestServerRule;

public class StateChangeIT {

    @Rule
    public RestmcuTestRule restmcu = new RestmcuTestRule();

    private NotifyResource notifyResource = new NotifyResource();

    @Rule
    public RestServerRule notifyRule = new RestServerRule("http://0.0.0.0:5879", notifyResource);

    @Test
    public void should_notify_on_state_change() throws Exception {

        RestMcuBoardSettings boardSettings = new RestMcuBoardSettings();
        boardSettings.setNotifyUrl("http://" + NetworkUtils.getFirstNonWifiIp() + ":5879/");
        restmcu.getBoardResource().setBoardSettings(boardSettings);
        RestMcuLineResource lineResource = restmcu.getLineResource();
        RestMcuLineSettings settings = new RestMcuLineSettings();
        settings.setNotifies(Arrays.asList(new RestMcuLineNotify(RestMcuLineNotifyCondition.SUP_OR_EQUAL, 1f)));
        lineResource.setLineSettings(54, settings);
        lineResource.setLineValue(9, 0f);

        lineResource.setLineValue(9, 1f);

        RestMcuLineNotification restMcuLineNotification = notifyResource.awaitLine().get(0);
        assertEquals(54, restMcuLineNotification.getLineId());
        assertEquals(new RestMcuLineNotify(RestMcuLineNotifyCondition.SUP_OR_EQUAL, 1f),
                restMcuLineNotification.getNotify());
        assertEquals(0f, restMcuLineNotification.getOldValue(), 0);
        assertEquals(1f, restMcuLineNotification.getValue(), 0);
        assertEquals(RestmcuTestContext.getUrl().substring(7, RestmcuTestContext.getUrl().length() - 1),
                restMcuLineNotification.getSource());
    }

    @Before
    public void before() throws Exception {
        Thread.sleep(60 * 1000);
        notifyResource.resetLatch();
    }
}
