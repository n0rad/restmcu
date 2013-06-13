package net.awired.restmcu.it;

import net.awired.jaxrs.client.server.rest.RestBuilder;
import net.awired.restmcu.api.filter.RestMcuSecurityClientInInterceptor;
import net.awired.restmcu.api.filter.RestMcuSecurityClientOutInterceptor;
import net.awired.restmcu.api.resource.client.RestMcuBoardResource;
import net.awired.restmcu.api.resource.client.RestMcuLineResource;
import org.junit.rules.ExternalResource;

public class RestmcuTestRule extends ExternalResource {

    private RestMcuBoardResource boardResource;

    private RestMcuLineResource lineResource;

    public RestmcuTestRule() {
        this(RestmcuTestContext.getUrl());
    }

    public RestmcuTestRule(String url) {

        RestBuilder context = new RestBuilder();

        context.addInInterceptor(new RestMcuSecurityClientInInterceptor(new RestmcuTestSecurityKey()));
        context.addOutInterceptor(new RestMcuSecurityClientOutInterceptor(new RestmcuTestSecurityKey()));

        lineResource = context.buildClient(RestMcuLineResource.class, url);
        boardResource = context.buildClient(RestMcuBoardResource.class, url);
    }

    @Override
    public void before() throws Throwable {
        reset();
    }

    public void reset() throws Exception {
        //        HccBoard deviceInfo = DefaultITDomainHelper.buildDefaultDevice();
        //        boardResource.setBoard(deviceInfo);

        //        for (int i = 0; i < deviceInfo.getNumberOfLine() - 1; i++) {
        //            HccLineInfo info = DefaultITDomainHelper.buildDefaultLine(i).getInfo();
        //            if (info != null) {
        //                lineResource.setLineInfo(i, info);
        //            }
        //        }
        //
        //        for (int i = 0; i < deviceInfo.getNumberOfLine() - 1; i++) {
        //            HccLine line = DefaultITDomainHelper.buildDefaultLine(i);
        //            if (line.getValue() != null) {
        //                debugResource.setDebugValue(i, line.getValue());
        //            }
        //        }
    }

    public RestMcuLineResource getLineResource() {
        return lineResource;
    }

    public RestMcuBoardResource getBoardResource() {
        return boardResource;
    }

}
