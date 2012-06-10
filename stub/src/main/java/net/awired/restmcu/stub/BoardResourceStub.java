package net.awired.restmcu.stub;

import net.awired.ajsl.core.lang.exception.UpdateException;
import net.awired.restmcu.api.domain.board.RestMcuBoard;
import net.awired.restmcu.api.resource.client.RestMcuBoardResource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class BoardResourceStub implements RestMcuBoardResource {

    @Autowired
    private HccContext context;

    @Override
    public void setBoard(RestMcuBoard board) throws UpdateException {
        // TODO Auto-generated method stub

    }

    @Override
    public void resetBoard() {
        // TODO Auto-generated method stub

    }

    @Override
    public void init() {
        // TODO Auto-generated method stub

    }

    @Override
    public void runNotify() {
        // TODO Auto-generated method stub

    }

    @Override
    public RestMcuBoard getBoard() {
        // TODO Auto-generated method stub
        return null;
    }

}
