package net.awired.restmcu.api.resource.client;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import net.awired.restmcu.api.domain.pin.HccPin;
import net.awired.restmcu.api.resource.HccPinNotFoundException;
import net.awired.restmcu.api.resource.HccUpdateException;

@Path("/pin/{pinId}")
@Produces("application/json")
@Consumes("application/json")
public interface HccPinResource {

    @GET
    HccPin getPin(@PathParam("pinId") int pinId) throws HccPinNotFoundException;

    @PUT
    void setPin(@PathParam("pinId") int pinId, HccPin pin) throws HccPinNotFoundException, HccUpdateException;

    @GET
    @Path("/value")
    Float getPinValue(@PathParam("pinId") int pinId) throws HccPinNotFoundException;

    @PUT
    @Path("/value")
    void setPinValue(@PathParam("pinId") int pinId, Float value) throws HccPinNotFoundException, HccUpdateException;
}
