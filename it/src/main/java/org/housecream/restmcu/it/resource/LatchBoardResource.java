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

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import org.housecream.restmcu.api.RestMcuUpdateException;
import org.housecream.restmcu.api.domain.board.RestMcuBoard;
import org.housecream.restmcu.api.domain.board.RestMcuBoardNotification;
import org.housecream.restmcu.api.domain.board.RestMcuBoardSettings;
import org.housecream.restmcu.api.domain.line.RestMcuLineNotification;
import org.housecream.restmcu.api.resource.client.RestMcuBoardResource;
import org.housecream.restmcu.api.resource.server.RestMcuNotifyResource;
import com.fasterxml.jackson.jaxrs.json.JacksonJsonProvider;
import fr.norad.jaxrs.client.server.resource.mapper.HttpStatusExceptionMapper;
import fr.norad.jaxrs.client.server.rest.RestBuilder;


public class LatchBoardResource implements RestMcuBoardResource {

    public final RestMcuBoard board = new RestMcuBoard();
    public final RestMcuBoardSettings boardSettings = new RestMcuBoardSettings();
    public final RestBuilder builder = new RestBuilder()
            .addProvider(new JacksonJsonProvider())
            .addInFaultInterceptor(RestBuilder.Generic.inStderrLogger)
            .addInInterceptor(RestBuilder.Generic.inStdoutLogger)
            .addOutFaultInterceptor(RestBuilder.Generic.outStderrLogger)
            .addOutInterceptor(RestBuilder.Generic.outStdoutLogger)
            .addProvider(new HttpStatusExceptionMapper());


    private CountDownLatch setLatch = new CountDownLatch(1);
    private final String source;

    public LatchBoardResource(String source) {
        this.source = source;
    }

    public void resetLatch() {
        setLatch = new CountDownLatch(1);
    }

    public void sendNotif(RestMcuLineNotification notif) {
        if (notif.getSource() == null) {
            notif.setSource(source);
        }
        RestMcuNotifyResource client = builder.buildClient(RestMcuNotifyResource.class,
                boardSettings.getNotifyUrl());
        client.lineNotification(notif);
    }

    public void sendNotif(RestMcuBoardNotification notif) {
        RestMcuNotifyResource client = builder.buildClient(RestMcuNotifyResource.class,
                boardSettings.getNotifyUrl());
        client.boardNotification(notif);
    }

    public RestMcuBoardSettings awaitUpdateSettings() throws InterruptedException {
        if (!setLatch.await(10, TimeUnit.SECONDS)) {
            throw new RuntimeException("Countdown timeout");
        }
        return boardSettings;
    }

    @Override
    public RestMcuBoard getBoard() {
        return board;
    }

    @Override
    public RestMcuBoardSettings getBoardSettings() {
        return boardSettings;
    }

    @Override
    public void setBoardSettings(RestMcuBoardSettings boardSettings) throws RestMcuUpdateException {
        if (boardSettings.getIp() != null) {
            this.boardSettings.setIp(boardSettings.getIp());
        }
        if (boardSettings.getName() != null) {
            this.boardSettings.setName(boardSettings.getName());
        }
        if (boardSettings.getNotifyUrl() != null) {
            this.boardSettings.setNotifyUrl(boardSettings.getNotifyUrl());
        }
        if (boardSettings.getPort() != null) {
            this.boardSettings.setPort(boardSettings.getPort());
        }
        setLatch.countDown();
    }

    @Override
    public void resetBoard() {
    }

    @Override
    public void runNotify() {
    }

}
