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
package org.housecream.restmcu.it;

import java.util.Arrays;
import org.apache.cxf.endpoint.Server;
import org.apache.cxf.transport.Destination;
import org.apache.cxf.transport.http_jetty.JettyHTTPDestination;
import org.apache.cxf.transport.http_jetty.JettyHTTPServerEngine;
import org.apache.cxf.transport.http_jetty.ServerEngine;
import org.housecream.restmcu.it.resource.NotifyResource;
import fr.norad.jaxrs.client.server.rest.RestBuilder;

public class RestmcuServerSample {

    private Server server;

    public RestmcuServerSample() {
        NotifyResource notifyResource = new NotifyResource();
        server = new RestBuilder().buildServer("http://0.0.0.0:6786", Arrays.asList(notifyResource));
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
