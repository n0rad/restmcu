package net.awired.restmcu.it;

import net.awired.core.lang.exception.UpdateException;
import net.awired.restmcu.api.domain.board.RestMcuBoard;
import net.awired.restmcu.api.domain.board.RestMcuBoardSettings;
import net.awired.restmcu.api.resource.client.RestMcuBoardResource;

public class TestBoard implements RestMcuBoardResource {

    @Override
    public RestMcuBoard getBoard() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return new RestMcuBoard();
    }

    @Override
    public void setBoardSettings(RestMcuBoardSettings boardSettings) throws UpdateException {
    }

    @Override
    public RestMcuBoardSettings getBoardSettings() {
        return new RestMcuBoardSettings();
    }

    @Override
    public void resetBoard() {
    }

    @Override
    public void runNotify() {
    }

}
