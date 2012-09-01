package net.awired.restmcu.api.resource.server;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import net.awired.restmcu.api.domain.board.RestMcuBoardNotification;
import net.awired.restmcu.api.domain.pin.RestMcuPinNotification;

@Produces("application/json")
@Consumes("application/json")
public interface RestMcuNotifyResource {

    @PUT
    @Path("/pin")
    public void pinNotification(RestMcuPinNotification pinNotification);

    @PUT
    @Path("/board")
    public void boardNotification(RestMcuBoardNotification boardNotification);

    /**
     * System.currentTimeMillis() / 1000L;
     */
    @GET
    @Path("/time")
    public long getPosixTime();

}
