package net.awired.restmcu.it;

import static org.junit.Assert.assertEquals;
import java.util.List;
import net.awired.ajsl.core.io.NetworkUtils;
import net.awired.ajsl.test.RestServerRule;
import net.awired.restmcu.api.domain.board.RestMcuBoardNotification;
import net.awired.restmcu.api.domain.board.RestMcuBoardNotificationType;
import net.awired.restmcu.api.domain.board.RestMcuBoardSettings;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

public class BoardNotifyIT {

    @Rule
    public RestMcuTestRule restmcu = new RestMcuTestRule();

    @Rule
    public RestServerRule notifyRule = new RestServerRule("http://0.0.0.0:5879", NotifyResource.class);

    @Test
    public void should_notify_server() throws Exception {

        RestMcuBoardSettings boardSettings = new RestMcuBoardSettings();
        boardSettings.setNotifyUrl("http://" + NetworkUtils.getFirstNonWifiIp() + ":5879/");
        restmcu.getBoardResource().setBoardSettings(boardSettings);

        restmcu.getBoardResource().runNotify();

        List<RestMcuBoardNotification> awaitBoard = notifyRule.getResource(NotifyResource.class).awaitBoard();
        assertEquals(1, awaitBoard.size());

        assertEquals(RestMcuBoardNotificationType.TEST, awaitBoard.get(0).getType());
    }

    @Before
    public void before() throws Exception {
        Thread.sleep(60 * 1000);
        notifyRule.getResource(NotifyResource.class).resetLatch();
    }

}
