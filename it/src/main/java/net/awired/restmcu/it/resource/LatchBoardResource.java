package net.awired.restmcu.it.resource;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import net.awired.ajsl.core.lang.exception.UpdateException;
import net.awired.ajsl.ws.rest.RestBuilder;
import net.awired.restmcu.api.domain.board.RestMcuBoard;
import net.awired.restmcu.api.domain.board.RestMcuBoardNotification;
import net.awired.restmcu.api.domain.board.RestMcuBoardSettings;
import net.awired.restmcu.api.domain.line.RestMcuLineNotification;
import net.awired.restmcu.api.resource.client.RestMcuBoardResource;
import net.awired.restmcu.api.resource.server.RestMcuNotifyResource;
import com.google.common.base.Preconditions;

public class LatchBoardResource implements RestMcuBoardResource {

    public final RestMcuBoard board = new RestMcuBoard();
    public final RestMcuBoardSettings boardSettings = new RestMcuBoardSettings();

    private CountDownLatch setLatch = new CountDownLatch(1);
    private final String source;

    public LatchBoardResource(String source) {
        this.source = source;
    }

    public void resetLatch() {
        setLatch = new CountDownLatch(1);
    }

    public void sendNotif(RestMcuLineNotification notif) {
        if (notif.getSource() == null) {
            notif.setSource(source);
        }
        Preconditions.checkNotNull(boardSettings.getNotifyUrl(), "notification url is mandatory");
        RestMcuNotifyResource client = new RestBuilder().buildClient(RestMcuNotifyResource.class,
                boardSettings.getNotifyUrl());
        client.lineNotification(notif);
    }

    public void sendNotif(RestMcuBoardNotification notif) {
        Preconditions.checkNotNull(boardSettings.getNotifyUrl(), "notification url is mandatory");
        RestMcuNotifyResource client = new RestBuilder().buildClient(RestMcuNotifyResource.class,
                boardSettings.getNotifyUrl());
        client.boardNotification(notif);
    }

    public RestMcuBoardSettings awaitUpdateSettings() throws InterruptedException {
        if (!setLatch.await(10, TimeUnit.SECONDS)) {
            throw new RuntimeException("Countdown timeout");
        }
        return boardSettings;
    }

    @Override
    public RestMcuBoard getBoard() {
        return board;
    }

    @Override
    public RestMcuBoardSettings getBoardSettings() {
        return boardSettings;
    }

    @Override
    public void setBoardSettings(RestMcuBoardSettings boardSettings) throws UpdateException {
        if (boardSettings.getIp() != null) {
            this.boardSettings.setIp(boardSettings.getIp());
        }
        if (boardSettings.getName() != null) {
            this.boardSettings.setName(boardSettings.getName());
        }
        if (boardSettings.getNotifyUrl() != null) {
            this.boardSettings.setNotifyUrl(boardSettings.getNotifyUrl());
        }
        if (boardSettings.getPort() != null) {
            this.boardSettings.setPort(boardSettings.getPort());
        }
        setLatch.countDown();
    }

    @Override
    public void resetBoard() {
    }

    @Override
    public void runNotify() {
    }

}
