package org.housecream.restmcu.it;

import org.housecream.restmcu.api.filter.RestMcuSecurityServerFilter;
import org.junit.Rule;
import org.junit.Test;
import fr.norad.jaxrs.junit.RestServerRule;

public class SecurityIT {

    @Rule
    public RestmcuTestRule restmcu = new RestmcuTestRule();

    @Rule
    public RestServerRule notifyRule = new RestServerRule("http://0.0.0.0:5879", TestBoard.class)
            .addProvider(new RestMcuSecurityServerFilter(new RestmcuTestSecurityKey()));

    @Test
    public void should_secure_transaction() {
        restmcu.getBoardResource().getBoard();
    }

}
