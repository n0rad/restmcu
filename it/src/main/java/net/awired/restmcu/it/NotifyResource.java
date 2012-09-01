package net.awired.restmcu.it;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Path;
import javax.ws.rs.core.Context;
import net.awired.restmcu.api.domain.board.RestMcuBoardNotification;
import net.awired.restmcu.api.domain.pin.RestMcuPinNotification;
import net.awired.restmcu.api.resource.server.RestMcuNotifyResource;

@Path("/")
public class NotifyResource implements RestMcuNotifyResource {

    private List<RestMcuBoardNotification> boardNotifications = new ArrayList<RestMcuBoardNotification>();
    private List<RestMcuPinNotification> pinNotifications = new ArrayList<RestMcuPinNotification>();

    private CountDownLatch boardLatch = new CountDownLatch(1);
    private CountDownLatch pinLatch = new CountDownLatch(1);

    @Context
    HttpServletRequest request;

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

    public void resetLatch() {
        boardLatch = new CountDownLatch(1);
        pinLatch = new CountDownLatch(1);
        boardNotifications = new ArrayList<RestMcuBoardNotification>();
        pinNotifications = new ArrayList<RestMcuPinNotification>();
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

    @Override
    public long getTime() {
        return System.currentTimeMillis();
    }

}
