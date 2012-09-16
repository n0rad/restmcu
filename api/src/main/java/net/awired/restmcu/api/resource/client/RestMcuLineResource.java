package net.awired.restmcu.api.resource.client;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import net.awired.ajsl.core.lang.exception.NotFoundException;
import net.awired.ajsl.core.lang.exception.UpdateException;
import net.awired.restmcu.api.domain.line.RestMcuLine;
import net.awired.restmcu.api.domain.line.RestMcuLineSettings;

@Path("/line/{lineId}")
@Produces("application/json")
@Consumes("application/json")
public interface RestMcuLineResource {

    @GET
    RestMcuLine getLine(@PathParam("lineId") Integer lineId) throws NotFoundException;

    @GET
    @Path("/settings")
    RestMcuLineSettings getLineSettings(@PathParam("lineId") Integer lineId) throws NotFoundException, UpdateException;

    @PUT
    @Path("/settings")
    void setLineSettings(@PathParam("lineId") Integer lineId, RestMcuLineSettings lineSettings) throws NotFoundException,
            UpdateException;

    @GET
    @Path("/value")
    Float getLineValue(@PathParam("lineId") Integer lineId) throws NotFoundException;

    @PUT
    @Path("/value")
    void setLineValue(@PathParam("lineId") Integer lineId, Float value) throws NotFoundException, UpdateException;
}
