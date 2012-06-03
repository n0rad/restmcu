package net.awired.restmcu.api.resource.test;

import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

@Path("/debug")
public interface RmcuDebugResource {

    /**
     * debug call to set value of input pin for client/stub tests only
     */
    @PUT
    @Path("/{pinId}/value")
    void setDebugValue(@PathParam("pinId") int pinId, Float value);

}
