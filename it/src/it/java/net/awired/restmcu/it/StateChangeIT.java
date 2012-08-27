package net.awired.restmcu.it;

import static org.junit.Assert.assertEquals;
import java.util.Arrays;
import net.awired.ajsl.core.io.NetworkUtils;
import net.awired.ajsl.test.RestServerRule;
import net.awired.restmcu.api.domain.board.RestMcuBoardSettings;
import net.awired.restmcu.api.domain.pin.RestMcuPinNotification;
import net.awired.restmcu.api.domain.pin.RestMcuPinNotify;
import net.awired.restmcu.api.domain.pin.RestMcuPinNotifyCondition;
import net.awired.restmcu.api.domain.pin.RestMcuPinSettings;
import net.awired.restmcu.api.resource.client.RestMcuPinResource;
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
        RestMcuPinResource pinResource = restmcu.getPinResource();
        RestMcuPinSettings settings = new RestMcuPinSettings();
        settings.setNotifies(Arrays.asList(new RestMcuPinNotify(RestMcuPinNotifyCondition.SUP_OR_EQUAL, 1f)));
        pinResource.setPinSettings(54, settings);
        pinResource.setPinValue(9, 0f);

        pinResource.setPinValue(9, 1f);

        RestMcuPinNotification restMcuPinNotification = notifyRule.getResource(NotifyResource.class).awaitPin()
                .get(0);
        assertEquals(54, restMcuPinNotification.getId());
        assertEquals(new RestMcuPinNotify(RestMcuPinNotifyCondition.SUP_OR_EQUAL, 1f),
                restMcuPinNotification.getNotify());
        assertEquals(0f, restMcuPinNotification.getOldValue(), 0);
        assertEquals(1f, restMcuPinNotification.getValue(), 0);
        assertEquals(RestMcuTestContext.getUrl().substring(7, RestMcuTestContext.getUrl().length() - 1),
                restMcuPinNotification.getSource());
    }

    @Before
    public void before() throws Exception {
        Thread.sleep(60 * 1000);
        notifyRule.getResource(NotifyResource.class).resetLatch();
    }
}
