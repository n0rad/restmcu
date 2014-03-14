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

import org.housecream.restmcu.api.LineNotFoundException;
import org.housecream.restmcu.api.RestMcuUpdateException;
import org.housecream.restmcu.api.domain.line.RestMcuLine;
import org.housecream.restmcu.api.domain.line.RestMcuLineSettings;

import javax.ws.rs.*;

@Path("/line/{lineId}")
@Produces("application/json")
@Consumes("application/json")
public interface RestMcuLineResource {

    @GET
    RestMcuLine getLine(@PathParam("lineId") Integer lineId) throws LineNotFoundException;

    @GET
    @Path("/settings")
    RestMcuLineSettings getLineSettings(@PathParam("lineId") Integer lineId) throws RestMcuUpdateException,
            LineNotFoundException;

    @PUT
    @Path("/settings")
    void setLineSettings(@PathParam("lineId") Integer lineId, RestMcuLineSettings lineSettings)
            throws RestMcuUpdateException, LineNotFoundException;

    @GET
    @Path("/value")
    Float getLineValue(@PathParam("lineId") Integer lineId) throws LineNotFoundException;

    @PUT
    @Path("/value")
    void setLineValue(@PathParam("lineId") Integer lineId, Float value) throws LineNotFoundException, RestMcuUpdateException;
}
