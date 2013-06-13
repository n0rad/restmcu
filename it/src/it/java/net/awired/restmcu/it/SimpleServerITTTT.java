package net.awired.restmcu.it;

import net.awired.core.io.NetworkUtils;
import net.awired.jaxrs.junit.RestServerRule;
import net.awired.restmcu.api.domain.board.RestMcuBoardSettings;
import net.awired.restmcu.api.filter.RestMcuSecurityClientInInterceptor;
import net.awired.restmcu.api.filter.RestMcuSecurityClientOutInterceptor;
import net.awired.restmcu.it.resource.NotifyResource;
import org.junit.Rule;
import org.junit.Test;

public class SimpleServerITTTT {
    @Rule
    public RestmcuTestRule restmcu = new RestmcuTestRule();

    @Rule
    public RestServerRule notifyRule = new RestServerRule("http://0.0.0.0:5879", NotifyResource.class)
            .addInInterceptor(new RestMcuSecurityClientInInterceptor(new RestmcuTestSecurityKey()))
            .addOutInterceptor(new RestMcuSecurityClientOutInterceptor(new RestmcuTestSecurityKey()));

    @Test
    public void should() throws Exception {
        RestMcuBoardSettings boardSettings = new RestMcuBoardSettings();
        boardSettings.setNotifyUrl("http://" + NetworkUtils.getFirstNonWifiIp() + ":5879/");
        //        restmcu.getBoardResource().setBoardSettings(boardSettings);
        Thread.sleep(10000000);
    }

}
