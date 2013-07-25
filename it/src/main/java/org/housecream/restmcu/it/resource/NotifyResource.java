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
