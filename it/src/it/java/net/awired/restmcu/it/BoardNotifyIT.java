package net.awired.restmcu.it;

import static org.junit.Assert.assertEquals;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import javax.ws.rs.Path;
import net.awired.ajsl.core.io.NetworkUtils;
import net.awired.ajsl.test.RestServerRule;
import net.awired.restmcu.api.domain.board.RestMcuBoardNotification;
import net.awired.restmcu.api.domain.board.RestMcuBoardNotificationType;
import net.awired.restmcu.api.domain.board.RestMcuBoardSettings;
import net.awired.restmcu.api.domain.pin.RestMcuPinNotification;
import net.awired.restmcu.api.resource.server.RestMcuNotifyResource;
import org.junit.Rule;
import org.junit.Test;

public class BoardNotifyIT {

    @Rule
    public RestMcuTestRule restmcu = new RestMcuTestRule();

    @Rule
    public RestServerRule notifyRule = new RestServerRule("http://0.0.0.0:5879", testNotify.class);

    @Path("/")
    public static class testNotify implements RestMcuNotifyResource {

        private List<RestMcuBoardNotification> boardNotifications = new ArrayList<RestMcuBoardNotification>();
        private List<RestMcuPinNotification> pinNotifications = new ArrayList<RestMcuPinNotification>();

        private CountDownLatch boardLatch = new CountDownLatch(1);
        private CountDownLatch pinLatch = new CountDownLatch(1);

        public List<RestMcuBoardNotification> awaitBoard() throws InterruptedException {
            if (!boardLatch.await(10, TimeUnit.SECONDS)) {
                throw new RuntimeException("Countdown timeout");
            }
            return boardNotifications;
        }

        public List<RestMcuPinNotification> awaitPin() throws InterruptedException {
            if (!pinLatch.await(10, TimeUnit.SECONDS)) {
                throw new RuntimeException("Countdown timeout");
            }
            return pinNotifications;
        }

        public void resetBoardLatch() {
            boardLatch = new CountDownLatch(1);
            pinLatch = new CountDownLatch(1);
        }

        @Override
        public void pinNotification(RestMcuPinNotification pinNotification) {
            pinNotifications.add(pinNotification);
            pinLatch.countDown();
        }

        @Override
        public void boardNotification(RestMcuBoardNotification boardNotification) {
            boardNotifications.add(boardNotification);
            boardLatch.countDown();
        }

    }

    @Test
    public void should_notify_server() throws Exception {
        RestMcuBoardSettings boardSettings = new RestMcuBoardSettings();
        boardSettings.setNotifyUrl("http://" + NetworkUtils.getFirstNonWifiIp() + ":5879/");
        restmcu.getBoardResource().setBoardSettings(boardSettings);

        restmcu.getBoardResource().runNotify();

        List<RestMcuBoardNotification> awaitBoard = notifyRule.getResource(testNotify.class).awaitBoard();
        assertEquals(1, awaitBoard.size());

        assertEquals(RestMcuBoardNotificationType.TEST, awaitBoard.get(0).getType());
    }

}
