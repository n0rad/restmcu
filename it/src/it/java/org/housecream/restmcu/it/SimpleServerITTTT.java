package org.housecream.restmcu.it;

import org.housecream.restmcu.api.domain.board.RestMcuBoardSettings;
import org.housecream.restmcu.api.filter.RestMcuSecurityClientInInterceptor;
import org.housecream.restmcu.api.filter.RestMcuSecurityClientOutInterceptor;
import org.housecream.restmcu.it.resource.NotifyResource;
import org.junit.Rule;
import org.junit.Test;
import fr.norad.core.io.NetworkUtils;
import fr.norad.jaxrs.client.server.rest.RestBuilder;
import fr.norad.jaxrs.junit.RestServerRule;

public class SimpleServerITTTT {
    @Rule
    public RestmcuTestRule restmcu = new RestmcuTestRule();

    @Rule
    public RestServerRule notifyRule = new RestServerRule(new RestBuilder() {
        {
            addInInterceptor(new RestMcuSecurityClientInInterceptor(new RestmcuTestSecurityKey()));
            addOutInterceptor(new RestMcuSecurityClientOutInterceptor(new RestmcuTestSecurityKey()));
        }
    }, "http://0.0.0.0:5879", NotifyResource.class);

    @Test
    public void should() throws Exception {
        RestMcuBoardSettings boardSettings = new RestMcuBoardSettings();
        boardSettings.setNotifyUrl("http://" + NetworkUtils.getFirstNonWifiIp() + ":5879/");
        //        restmcu.getBoardResource().setBoardSettings(boardSettings);
        Thread.sleep(10000000);
    }

}
