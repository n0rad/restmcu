package net.awired.restmcu.it;

import static net.awired.restmcu.api.domain.line.RestMcuLineNotifyCondition.INF_OR_EQUAL;
import static net.awired.restmcu.api.domain.line.RestMcuLineNotifyCondition.SUP_OR_EQUAL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import net.awired.ajsl.ws.rest.RestContext;
import net.awired.restmcu.api.domain.line.RestMcuLine;
import net.awired.restmcu.api.domain.line.RestMcuLineDirection;
import net.awired.restmcu.api.domain.line.RestMcuLineNotify;
import net.awired.restmcu.api.domain.line.RestMcuLineSettings;
import net.awired.restmcu.api.domain.line.RestMcuLineType;
import net.awired.restmcu.it.resource.EmulatorBoardResource;
import net.awired.restmcu.it.resource.EmulatorLineResource;
import net.awired.restmcu.it.resource.LineInfo;
import org.apache.cxf.endpoint.Server;
import org.apache.cxf.transport.Destination;
import org.apache.cxf.transport.http_jetty.JettyHTTPDestination;
import org.apache.cxf.transport.http_jetty.JettyHTTPServerEngine;
import org.apache.cxf.transport.http_jetty.ServerEngine;

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
        server = new RestContext().prepareServer(listenAddress, Arrays.asList(board, line));

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
