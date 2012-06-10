package net.awired.restmcu.api.resource.server;

import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import net.awired.restmcu.api.domain.board.RestMcuBoardNotification;
import net.awired.restmcu.api.domain.pin.RestMcuPinNotification;

public interface RestMcuNotifyResource {

    @PUT
    @Path("/pin")
    public void pinNotification(RestMcuPinNotification pinNotification);

    @PUT
    @Path("/board")
    public void boardNotification(RestMcuBoardNotification boardNotification);

}
