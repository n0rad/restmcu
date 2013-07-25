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
package org.housecream.restmcu.api.resource.server;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import org.housecream.restmcu.api.domain.board.RestMcuBoardNotification;
import org.housecream.restmcu.api.domain.line.RestMcuLineNotification;

@Produces("application/json")
@Consumes("application/json")
public interface RestMcuNotifyResource {

    @PUT
    @Path("/line")
    public void lineNotification(RestMcuLineNotification lineNotification);

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
