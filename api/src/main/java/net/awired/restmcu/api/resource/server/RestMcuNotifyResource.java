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
package net.awired.restmcu.api.resource.server;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import net.awired.restmcu.api.domain.board.RestMcuBoardNotification;
import net.awired.restmcu.api.domain.line.RestMcuLineNotification;

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
