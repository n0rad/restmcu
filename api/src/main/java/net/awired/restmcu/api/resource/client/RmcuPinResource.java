package net.awired.restmcu.api.resource.client;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import net.awired.ajsl.core.lang.exception.NotFoundException;
import net.awired.ajsl.core.lang.exception.UpdateException;
import net.awired.restmcu.api.domain.pin.RmcuPin;

@Path("/pin/{pinId}")
@Produces("application/json")
@Consumes("application/json")
public interface RmcuPinResource {

    @GET
    RmcuPin getPin(@PathParam("pinId") int pinId) throws NotFoundException;

    @PUT
    void setPin(@PathParam("pinId") int pinId, RmcuPin pin) throws NotFoundException, UpdateException;

    @GET
    @Path("/value")
    Float getPinValue(@PathParam("pinId") int pinId) throws NotFoundException;

    @PUT
    @Path("/value")
    void setPinValue(@PathParam("pinId") int pinId, Float value) throws NotFoundException, UpdateException;
}
