/**
 *
 *     Copyright (C) Housecream.org
 *
 *     This program is free software: you can redistribute it and/or modify
 *     it under the terms of the GNU Affero General Public License as published by
 *     the Free Software Foundation, either version 3 of the License, or any later version.
 *
 *     This program is distributed in the hope that it will be useful,
 *     but WITHOUT ANY WARRANTY; without even the implied warranty of
 *     MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *     GNU Affero General Public License for more details.
 *
 *     You should have received a copy of the GNU Affero General Public License
 *     along with this program.  If not, see <http://www.gnu.org/licenses/>
 */
package org.housecream.restmcu.api.resource.client;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import org.housecream.restmcu.api.domain.line.RestMcuLine;
import org.housecream.restmcu.api.domain.line.RestMcuLineSettings;
import fr.norad.core.lang.exception.NotFoundException;
import fr.norad.core.lang.exception.UpdateException;

@Path("/line/{lineId}")
@Produces("application/json")
@Consumes("application/json")
public interface RestMcuLineResource {

    @GET
    RestMcuLine getLine(@PathParam("lineId") Integer lineId) throws NotFoundException;

    @GET
    @Path("/settings")
    RestMcuLineSettings getLineSettings(@PathParam("lineId") Integer lineId) throws NotFoundException,
            UpdateException;

    @PUT
    @Path("/settings")
    void setLineSettings(@PathParam("lineId") Integer lineId, RestMcuLineSettings lineSettings)
            throws NotFoundException, UpdateException;

    @GET
    @Path("/value")
    Float getLineValue(@PathParam("lineId") Integer lineId) throws NotFoundException;

    @PUT
    @Path("/value")
    void setLineValue(@PathParam("lineId") Integer lineId, Float value) throws NotFoundException, UpdateException;
}
