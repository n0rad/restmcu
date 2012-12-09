package net.awired.restmcu.it;

import java.util.Arrays;
import net.awired.ajsl.web.rest.RestContext;
import net.awired.restmcu.it.resource.NotifyResource;
import org.apache.cxf.endpoint.Server;
import org.apache.cxf.transport.Destination;
import org.apache.cxf.transport.http_jetty.JettyHTTPDestination;
import org.apache.cxf.transport.http_jetty.JettyHTTPServerEngine;
import org.apache.cxf.transport.http_jetty.ServerEngine;

public class RestmcuServerSample {

    private Server server;

    public RestmcuServerSample() {
        NotifyResource notifyResource = new NotifyResource();
        server = new RestContext().prepareServer("http://0.0.0.0:6786", Arrays.asList(notifyResource));
        Runtime.getRuntime().addShutdownHook(new Thread() {
            @Override
            public void run() {
                server.stop();
            }
        });
    }

    public static void main(String[] args) throws InterruptedException {
        RestmcuServerSample restmcuSample = new RestmcuServerSample();

        Destination dest = restmcuSample.server.getDestination();

        JettyHTTPDestination jettyDestination = JettyHTTPDestination.class.cast(dest);
        ServerEngine engine = jettyDestination.getEngine();
        JettyHTTPServerEngine serverEngine = JettyHTTPServerEngine.class.cast(engine);
        org.eclipse.jetty.server.Server httpServer = serverEngine.getServer();

        httpServer.join();
    }
}
