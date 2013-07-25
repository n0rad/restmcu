package org.housecream.restmcu.it;

import org.housecream.restmcu.api.domain.board.RestMcuBoard;
import org.housecream.restmcu.api.domain.board.RestMcuBoardSettings;
import org.housecream.restmcu.api.resource.client.RestMcuBoardResource;
import fr.norad.core.lang.exception.UpdateException;

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
