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
package org.housecream.restmcu.it.resource;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Path;
import javax.ws.rs.core.Context;
import org.housecream.restmcu.api.domain.board.RestMcuBoardNotification;
import org.housecream.restmcu.api.domain.line.RestMcuLineNotification;
import org.housecream.restmcu.api.resource.server.RestMcuNotifyResource;

@Path("/")
public class NotifyResource implements RestMcuNotifyResource {

    private List<RestMcuBoardNotification> boardNotifications = new ArrayList<RestMcuBoardNotification>();
    private List<RestMcuLineNotification> lineNotifications = new ArrayList<RestMcuLineNotification>();

    private CountDownLatch boardLatch = new CountDownLatch(1);
    private CountDownLatch lineLatch = new CountDownLatch(1);

    @Context
    HttpServletRequest request;

    public List<RestMcuBoardNotification> awaitBoard() throws InterruptedException {
        if (!boardLatch.await(10, TimeUnit.SECONDS)) {
            throw new RuntimeException("Countdown timeout");
        }
        return boardNotifications;
    }

    public List<RestMcuLineNotification> awaitLine() throws InterruptedException {
        if (!lineLatch.await(10, TimeUnit.SECONDS)) {
            throw new RuntimeException("Countdown timeout");
        }
        return lineNotifications;
    }

    public void resetLatch() {
        boardLatch = new CountDownLatch(1);
        lineLatch = new CountDownLatch(1);
        boardNotifications = new ArrayList<RestMcuBoardNotification>();
        lineNotifications = new ArrayList<RestMcuLineNotification>();
    }

    @Override
    public void lineNotification(RestMcuLineNotification lineNotification) {
        lineNotifications.add(lineNotification);
        lineLatch.countDown();
    }

    @Override
    public void boardNotification(RestMcuBoardNotification boardNotification) {
        boardNotifications.add(boardNotification);
        boardLatch.countDown();
    }

    @Override
    public long getPosixTime() {
        return System.currentTimeMillis() / 1000L;
    }

}
