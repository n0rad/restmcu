package net.awired.restmcu.it;

import static com.sun.jersey.api.core.ResourceConfig.PROPERTY_CONTAINER_REQUEST_FILTERS;
import static com.sun.jersey.api.core.ResourceConfig.PROPERTY_CONTAINER_RESPONSE_FILTERS;
import net.awired.restmcu.api.resource.server.RestMcuNotifyResource;
import org.glassfish.grizzly.http.server.HttpServer;
import org.junit.rules.ExternalResource;
import com.sun.jersey.api.container.filter.LoggingFilter;
import com.sun.jersey.api.container.grizzly2.GrizzlyServerFactory;
import com.sun.jersey.api.core.DefaultResourceConfig;

public class NotifyServerRule extends ExternalResource {

    private int port;
    private HttpServer server;
    private final Class<? extends RestMcuNotifyResource> resource;

    public NotifyServerRule(int port, Class<? extends RestMcuNotifyResource> resource) {
        this.resource = resource;
        this.port = port;
    }

    @Override
    public void before() throws Throwable {
        //        ResourceConfig rc = newConfig();
        //        server = GrizzlyServerFactory.createHttpServer(uri, rc);

        DefaultResourceConfig resourceConfig = new DefaultResourceConfig(resource);
        //        DefaultResourceConfig resourceConfig = new DefaultResourceConfig(resource);
        resourceConfig.getProperties().put(PROPERTY_CONTAINER_REQUEST_FILTERS, new LoggingFilter());
        resourceConfig.getProperties().put(PROPERTY_CONTAINER_RESPONSE_FILTERS, new LoggingFilter());

        // The following line is to enable GZIP when client accepts it
        //        resourceConfig.getContainerResponseFilters().add(new GZIPContentEncodingFilter());
        server = GrizzlyServerFactory.createHttpServer("http://localhost:" + port, resourceConfig);
    }

    public static void main(String[] args) throws Throwable {
        NotifyServerRule notifyServer = new NotifyServerRule(8080, null);
        notifyServer.before();
        while (true) {
            Thread.sleep(1000);
        }
    }

    @Override
    public void after() {
        server.stop();
    }
}
