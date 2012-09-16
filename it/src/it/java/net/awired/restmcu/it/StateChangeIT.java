package net.awired.restmcu.it;

import static org.junit.Assert.assertEquals;
import java.util.Arrays;
import net.awired.ajsl.core.io.NetworkUtils;
import net.awired.ajsl.test.RestServerRule;
import net.awired.restmcu.api.domain.board.RestMcuBoardSettings;
import net.awired.restmcu.api.domain.line.RestMcuLineNotification;
import net.awired.restmcu.api.domain.line.RestMcuLineNotify;
import net.awired.restmcu.api.domain.line.RestMcuLineNotifyCondition;
import net.awired.restmcu.api.domain.line.RestMcuLineSettings;
import net.awired.restmcu.api.resource.client.RestMcuLineResource;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

public class StateChangeIT {

    @Rule
    public RestMcuTestRule restmcu = new RestMcuTestRule();

    @Rule
    public RestServerRule notifyRule = new RestServerRule("http://0.0.0.0:5879", NotifyResource.class);

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

        RestMcuLineNotification restMcuLineNotification = notifyRule.getResource(NotifyResource.class).awaitLine()
                .get(0);
        assertEquals(54, restMcuLineNotification.getId());
        assertEquals(new RestMcuLineNotify(RestMcuLineNotifyCondition.SUP_OR_EQUAL, 1f),
                restMcuLineNotification.getNotify());
        assertEquals(0f, restMcuLineNotification.getOldValue(), 0);
        assertEquals(1f, restMcuLineNotification.getValue(), 0);
        assertEquals(RestMcuTestContext.getUrl().substring(7, RestMcuTestContext.getUrl().length() - 1),
                restMcuLineNotification.getSource());
    }

    @Before
    public void before() throws Exception {
        Thread.sleep(60 * 1000);
        notifyRule.getResource(NotifyResource.class).resetLatch();
    }
}
