package org.housecream.restmcu.it;

import fr.norad.jaxrs.client.server.rest.RestBuilder;
import fr.norad.jaxrs.junit.RestServerRule;
import org.junit.Rule;
import org.junit.Test;

public class SecurityIT {

    @Rule
    public RestmcuTestRule restmcu = new RestmcuTestRule();

    @Rule
    public RestServerRule notifyRule = new RestServerRule(new RestBuilder() {
        {
//            addProvider(new RestMcuSecurityServerFilter(new RestmcuTestSecurityKey()));
        }
    }, "http://0.0.0.0:5879", TestBoard.class);

    @Test
    public void should_secure_transaction() {
        restmcu.getBoardResource().getBoard();
    }

}
