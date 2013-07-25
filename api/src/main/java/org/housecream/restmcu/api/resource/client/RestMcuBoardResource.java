/**
 *
 *     Copyright (C) Awired.net
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
import javax.ws.rs.Produces;
import org.housecream.restmcu.api.domain.board.RestMcuBoard;
import org.housecream.restmcu.api.domain.board.RestMcuBoardSettings;
import fr.norad.core.lang.exception.UpdateException;

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
    @Path("/notify")
    public void runNotify();

}
