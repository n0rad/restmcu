package net.awired.restmcu.api.resource.server;

import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import net.awired.restmcu.api.domain.board.HccBoardNotification;
import net.awired.restmcu.api.domain.pin.HccPinNotification;

public interface HccNotifyResource {

    @PUT
    @Path("/pin")
    public void pinNotification(HccPinNotification pinNotification);

    @PUT
    @Path("/board")
    public void boardNotification(HccBoardNotification boardNotification);

}
