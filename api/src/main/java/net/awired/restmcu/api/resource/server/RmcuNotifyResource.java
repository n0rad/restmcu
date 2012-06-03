package net.awired.restmcu.api.resource.server;

import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import net.awired.restmcu.api.domain.board.RmcuBoardNotification;
import net.awired.restmcu.api.domain.pin.RmcuPinNotification;

public interface RmcuNotifyResource {

    @PUT
    @Path("/pin")
    public void pinNotification(RmcuPinNotification pinNotification);

    @PUT
    @Path("/board")
    public void boardNotification(RmcuBoardNotification boardNotification);

}
