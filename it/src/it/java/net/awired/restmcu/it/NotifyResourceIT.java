package net.awired.restmcu.it;

import static org.junit.Assert.assertNotNull;
import javax.ws.rs.Path;
import net.awired.restmcu.api.domain.board.RestMcuBoard;
import net.awired.restmcu.api.domain.board.RestMcuBoardNotification;
import net.awired.restmcu.api.domain.pin.RestMcuPinNotification;
import net.awired.restmcu.api.resource.server.RestMcuNotifyResource;
import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;

public class NotifyResourceIT {

    @Rule
    public RestMcuTestRule hcc = new RestMcuTestRule();

    public static RestMcuPinNotification notification;

    @Path("/")
    public static class testNotify implements RestMcuNotifyResource {
        @Override
        public void pinNotification(RestMcuPinNotification pinNotification) {
            //            HccPinNotification pinNotification
            System.out.println("salutttt!");
            notification = pinNotification;
            throw new RuntimeException();
        }

        @Override
        public void boardNotification(RestMcuBoardNotification boardNotification) {
            throw new RuntimeException();
        }

    }

    @ClassRule
    public static NotifyServerRule server = new NotifyServerRule(5879, false, testNotify.class);

    @Test
    public void should_notify() throws Exception {
        RestMcuBoard deviceInfo = hcc.getBoardResource().getBoard();
        deviceInfo.setNotifyUrl("http://localhost:5879");
        hcc.getBoardResource().setBoard(deviceInfo);

        // change value
        hcc.getDebugResource().setDebugValue(5, 1f);

        // change value
        hcc.getDebugResource().setDebugValue(5, 0f);

        assertNotNull(notification);
    }

    @Test
    public void should_reset_data() throws Exception {
        // set notify url
        RestMcuBoard deviceInfo = hcc.getBoardResource().getBoard();
        deviceInfo.setNotifyUrl("http://localhost:5879");
        hcc.getBoardResource().setBoard(deviceInfo);

        // change value
        hcc.getDebugResource().setDebugValue(5, 1f);

        // change value
        hcc.getDebugResource().setDebugValue(5, 0f);

        assertNotNull(notification);
    }
}
