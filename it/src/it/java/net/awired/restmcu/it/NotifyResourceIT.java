package net.awired.restmcu.it;

import static org.junit.Assert.assertNotNull;
import javax.ws.rs.Path;
import net.awired.restmcu.api.domain.board.RmcuBoard;
import net.awired.restmcu.api.domain.board.RmcuBoardNotification;
import net.awired.restmcu.api.domain.pin.RmcuPinNotification;
import net.awired.restmcu.api.resource.server.RmcuNotifyResource;
import org.junit.ClassRule;
import org.junit.Ignore;
import org.junit.Rule;
import org.junit.Test;

@Ignore
public class NotifyResourceIT {

    @Rule
    public RmcuTestRule hcc = new RmcuTestRule();

    public static RmcuPinNotification notification;

    @Path("/")
    public static class testNotify implements RmcuNotifyResource {
        @Override
        public void pinNotification(RmcuPinNotification pinNotification) {
            //            HccPinNotification pinNotification
            System.out.println("salutttt!");
            notification = pinNotification;
            throw new RuntimeException();
        }

        @Override
        public void boardNotification(RmcuBoardNotification boardNotification) {
            throw new RuntimeException();
        }

    }

    @ClassRule
    public static NotifyServerRule server = new NotifyServerRule(5879, testNotify.class);

    @Test
    public void should_notify() throws Exception {
        RmcuBoard deviceInfo = hcc.getBoardResource().getBoard();
        deviceInfo.setNotifyUrl("http://localhost:5879");
        hcc.getBoardResource().setBoard(deviceInfo);

        // change value
        hcc.getDebugResource().setDebugValue(5, 1f);

        // change value
        hcc.getDebugResource().setDebugValue(5, 0f);

        assertNotNull(notification);
    }

    @Test
    @Ignore
    public void should_reset_data() throws Exception {
        // set notify url
        RmcuBoard deviceInfo = hcc.getBoardResource().getBoard();
        deviceInfo.setNotifyUrl("http://localhost:5879");
        hcc.getBoardResource().setBoard(deviceInfo);

        // change value
        hcc.getDebugResource().setDebugValue(5, 1f);

        // change value
        hcc.getDebugResource().setDebugValue(5, 0f);

        assertNotNull(notification);
    }
}
