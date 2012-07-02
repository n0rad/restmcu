package net.awired.restmcu.it;

import java.util.Arrays;
import net.awired.ajsl.web.resource.mapper.AjslExceptionMapper;
import org.apache.cxf.endpoint.Server;
import org.apache.cxf.interceptor.LoggingInInterceptor;
import org.apache.cxf.interceptor.LoggingOutInterceptor;
import org.apache.cxf.jaxrs.JAXRSServerFactoryBean;
import org.apache.cxf.jaxrs.provider.JSONProvider;
import org.junit.rules.ExternalResource;

public class NotifyServerRule extends ExternalResource {
    private int port;
    private final Class<?>[] resources;
    private Server server;
    private final boolean logExchange;

    public NotifyServerRule(int port, boolean logExchange, Class<?>... resources) {
        this.logExchange = logExchange;
        this.resources = resources;
        this.port = port;
    }

    @Override
    public void before() throws Throwable {
        JAXRSServerFactoryBean factory = new JAXRSServerFactoryBean();

        JSONProvider jsonProvider = new JSONProvider();
        jsonProvider.setSupportUnwrapped(true);
        jsonProvider.setDropRootElement(true);

        if (logExchange) {
            factory.getInInterceptors().add(new LoggingInInterceptor());
            factory.getOutInterceptors().add(new LoggingOutInterceptor());
        }

        factory.setProviders(Arrays.asList(jsonProvider, new AjslExceptionMapper()));

        factory.setResourceClasses(resources);
        factory.setAddress("http://0.0.0.0:" + port + "/");
        server = factory.create();
    }

    @Override
    public void after() {
        server.stop();
    }

}
