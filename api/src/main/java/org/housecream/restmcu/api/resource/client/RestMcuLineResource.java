/**
 *
 *     Copyright (C) norad.fr
 *
 *     Licensed under the Apache License, Version 2.0 (the "License");
 *     you may not use this file except in compliance with the License.
 *     You may obtain a copy of the License at
 *
 *             http://www.apache.org/licenses/LICENSE-2.0
 *
 *     Unless required by applicable law or agreed to in writing, software
 *     distributed under the License is distributed on an "AS IS" BASIS,
 *     WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *     See the License for the specific language governing permissions and
 *     limitations under the License.
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
