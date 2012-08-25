package net.awired.restmcu.api.resource.client;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import net.awired.ajsl.core.lang.exception.UpdateException;
import net.awired.restmcu.api.domain.board.RestMcuBoard;
import net.awired.restmcu.api.domain.board.RestMcuBoardSettings;

@Path("/")
@Produces("application/json")
@Consumes("application/json")
public interface RestMcuBoardResource {

    @GET
    public RestMcuBoard getBoard();

    @PUT
    @Path("/settings")
    public void setBoardSettings(RestMcuBoardSettings boardSettings) throws UpdateException;

    @GET
    @Path("/settings")
    public RestMcuBoardSettings getBoardSettings();

    @PUT
    @Path("/reset")
    public void resetBoard();

    @PUT
    @Path("/init")
    public void init();

    @PUT
    @Path("/notify")
    public void runNotify();

}
