package net.awired.restmcu.it.interactive;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import net.awired.restmcu.api.resource.client.RestMcuBoardResource;
import net.awired.restmcu.it.RestMcuTestRule;
import org.junit.Rule;
import org.junit.Test;

public class GreenLedSwitcherIT {

    @Rule
    public RestMcuTestRule hcc = new RestMcuTestRule();

    @Test
    public void should_toggle_green_led() throws Exception {
        for (int i = 0; i < 100; i++) {
            hcc.getPinResource().setPinValue(3, 1f);
            hcc.getPinResource().setPinValue(3, 0f);
        }
    }

    @Test
    public void should_toggle_green_led2() throws Exception {
        RestMcuBoardResource boardResource = hcc.getBoardResource();
        for (int i = 0; i < 100; i++) {
            //            boardResource.getBoard();
            //                        pinResource.getPinValue(3);
            hcc.getPinResource().setPinValue(3, 1f);
            hcc.getPinResource().setPinValue(3, 0f);
        }
        long currentTimeMillis = System.currentTimeMillis();
        for (int i = 0; i < 10000; i++) {
            boardResource.getBoard();
            //            pinResource.getPinValue(3);
            hcc.getPinResource().setPinValue(3, 1f);
            hcc.getPinResource().setPinValue(3, 0f);
        }
        System.out.println(System.currentTimeMillis() - currentTimeMillis);
    }

    @Test
    public void should_toggle_green_led5() throws Exception {
        RestMcuBoardResource boardResource = hcc.getBoardResource();
        for (int i = 0; i < 100; i++) {
            //            boardResource.getBoard();
            //                        pinResource.getPinValue(3);
            hcc.getPinResource().setPinValue(5, 1f);
            hcc.getPinResource().setPinValue(5, 0f);
        }
        long currentTimeMillis = System.currentTimeMillis();
        for (int i = 0; i < 10000; i++) {
            boardResource.getBoard();
            //            pinResource.getPinValue(3);
            hcc.getPinResource().setPinValue(5, 1f);
            hcc.getPinResource().setPinValue(5, 0f);
        }
        System.out.println(System.currentTimeMillis() - currentTimeMillis);
    }

    @Test
    public void test() throws Exception {

        URL url = new URL("http://192.168.42.244");
        for (int i = 0; i < 100; i++) {
            dfdf(url);
        }
        long currentTimeMillis = System.currentTimeMillis();
        for (int i = 0; i < 200; i++) {
            dfdf(url);
        }
        System.out.println(System.currentTimeMillis() - currentTimeMillis);

    }

    private void dfdf(URL url) throws IOException {

        InputStream openStream = url.openStream();

        try {

            int read;
            byte[] cbuf = new byte[1024];
            while ((read = openStream.read(cbuf)) != -1) {

            }

        } finally {
            openStream.close();
        }
    }
}
