package org.housecream.restmcu.it;

import static org.junit.Assert.assertEquals;
import java.util.List;
import org.housecream.restmcu.api.domain.board.RestMcuBoardNotification;
import org.housecream.restmcu.api.domain.board.RestMcuBoardNotificationType;
import org.housecream.restmcu.api.domain.board.RestMcuBoardSettings;
import org.housecream.restmcu.it.resource.NotifyResource;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import fr.norad.core.io.NetworkUtils;
import fr.norad.jaxrs.junit.RestServerRule;

public class BoardNotifyIT {

    @Rule
    public RestmcuTestRule restmcu = new RestmcuTestRule();

    private NotifyResource notifyResource = new NotifyResource();

    @Rule
    public RestServerRule notifyRule = new RestServerRule("http://0.0.0.0:5879", notifyResource);

    @Test
    public void should_notify_server() throws Exception {

        RestMcuBoardSettings boardSettings = new RestMcuBoardSettings();
        boardSettings.setNotifyUrl("http://" + NetworkUtils.getFirstNonWifiIp() + ":5879/");
        restmcu.getBoardResource().setBoardSettings(boardSettings);

        restmcu.getBoardResource().runNotify();

        List<RestMcuBoardNotification> awaitBoard = notifyResource.awaitBoard();
        assertEquals(1, awaitBoard.size());

        assertEquals(RestMcuBoardNotificationType.TEST, awaitBoard.get(0).getType());
    }

    @Before
    public void before() throws Exception {
        Thread.sleep(60 * 1000);
        notifyResource.resetLatch();
    }

}
