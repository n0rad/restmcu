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
package org.housecream.restmcu.it;

import static org.housecream.restmcu.api.domain.line.RestMcuLineNotifyCondition.INF_OR_EQUAL;
import static org.housecream.restmcu.api.domain.line.RestMcuLineNotifyCondition.SUP_OR_EQUAL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.apache.cxf.endpoint.Server;
import org.apache.cxf.transport.Destination;
import org.apache.cxf.transport.http_jetty.JettyHTTPDestination;
import org.apache.cxf.transport.http_jetty.JettyHTTPServerEngine;
import org.apache.cxf.transport.http_jetty.ServerEngine;
import org.housecream.restmcu.api.domain.line.RestMcuLine;
import org.housecream.restmcu.api.domain.line.RestMcuLineDirection;
import org.housecream.restmcu.api.domain.line.RestMcuLineNotify;
import org.housecream.restmcu.api.domain.line.RestMcuLineSettings;
import org.housecream.restmcu.api.domain.line.RestMcuLineType;
import org.housecream.restmcu.it.resource.EmulatorBoardResource;
import org.housecream.restmcu.it.resource.EmulatorLineResource;
import org.housecream.restmcu.it.resource.LineInfo;
import fr.norad.jaxrs.client.server.rest.RestBuilder;

public class RestmcuSample {

    private Server server;

    public RestmcuSample() {
        EmulatorBoardResource board = new EmulatorBoardResource("127.0.0.1", 8976);
        board.board.setDescription("description of the board");
        board.board.setHardware("RestMcu");
        board.board.setLineIds(Arrays.asList(5, 6, 7, 8));
        board.board.setMac("45:45:45:45:45");
        board.board.setVersion("1.0");
        board.boardSettings.setName("board name");
        board.boardSettings.setNotifyUrl("NOTSETYET");

        EmulatorLineResource line = new EmulatorLineResource(board);
        line.addLine(fillPin());

        String listenAddress = "http://" + board.boardSettings.getIp() + ":" + board.boardSettings.getPort();
        server = new RestBuilder().buildServer(listenAddress, Arrays.asList(board, line));

        Runtime.getRuntime().addShutdownHook(new Thread() {
            @Override
            public void run() {
                server.stop();
            }
        });
    }

    public LineInfo fillPin() {
        LineInfo line = new LineInfo(42);
        line.setDescription(new RestMcuLine());
        line.getDescription().setDescription("pin 42 description");
        line.getDescription().setDirection(RestMcuLineDirection.INPUT);
        line.getDescription().setType(RestMcuLineType.ANALOG);
        line.getDescription().setValueMax(1024f);
        line.getDescription().setValueMin(0f);

        line.setSettings(new RestMcuLineSettings());
        line.getSettings().setName("name of pin");
        List<RestMcuLineNotify> notifies = new ArrayList<RestMcuLineNotify>();
        notifies.add(new RestMcuLineNotify(SUP_OR_EQUAL, 42));
        notifies.add(new RestMcuLineNotify(INF_OR_EQUAL, 42));

        line.getSettings().setNotifies(notifies);

        return line;
    }

    public static void main(String[] args) throws InterruptedException {
        RestmcuSample restmcuSample = new RestmcuSample();

        Destination dest = restmcuSample.server.getDestination();

        JettyHTTPDestination jettyDestination = JettyHTTPDestination.class.cast(dest);
        ServerEngine engine = jettyDestination.getEngine();
        JettyHTTPServerEngine serverEngine = JettyHTTPServerEngine.class.cast(engine);
        org.eclipse.jetty.server.Server httpServer = serverEngine.getServer();

        httpServer.join();
    }
}
