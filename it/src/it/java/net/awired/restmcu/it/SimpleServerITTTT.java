package net.awired.restmcu.it;

import java.security.MessageDigest;
import java.util.Arrays;
import javax.crypto.Mac;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import net.awired.ajsl.core.io.NetworkUtils;
import net.awired.ajsl.test.RestServerRule;
import net.awired.restmcu.api.domain.board.RestMcuBoardSettings;
import net.awired.restmcu.api.filter.RestMcuSecurityFilter;
import org.junit.Rule;
import org.junit.Test;

public class SimpleServerITTTT {
    @Rule
    public RestMcuTestRule restmcu = new RestMcuTestRule();

    @Rule
    public RestServerRule notifyRule = new RestServerRule("http://0.0.0.0:5879",
            Arrays.asList(new RestMcuSecurityFilter(new RestMcuTestSecurityKey())), NotifyResource.class);

    @Test
    public void should() throws Exception {
        RestMcuBoardSettings boardSettings = new RestMcuBoardSettings();
        boardSettings.setNotifyUrl("http://" + NetworkUtils.getFirstNonWifiIp() + ":5879/");
        //        restmcu.getBoardResource().setBoardSettings(boardSettings);
        Thread.sleep(10000000);
    }

    public static void main(String[] args) throws Exception {
        SecretKey signingKey = new SecretKeySpec("TOTO42".getBytes(), "HMACSHA256");
        Mac mac = Mac.getInstance("HMACSHA256");
        mac.init(signingKey);
        byte[] digest = mac.doFinal("MESSAGE".getBytes("ASCII"));

        StringBuffer sb = new StringBuffer();
        for (byte element : digest) {
            sb.append(Integer.toString((element & 0xff) + 0x100, 16).substring(1));
        }

        System.out.println("Hex format : " + sb.toString());

    }

    public static void main2(String[] args) throws Exception {

        String password = "TOTO42";

        MessageDigest md = MessageDigest.getInstance("SHA-256");
        md.update(password.getBytes());

        byte byteData[] = md.digest();

        //convert the byte to hex format method 1
        StringBuffer sb = new StringBuffer();
        for (byte element : byteData) {
            sb.append(Integer.toString((element & 0xff) + 0x100, 16).substring(1));
        }

        System.out.println("Hex format : " + sb.toString());

        //convert the byte to hex format method 2
        StringBuffer hexString = new StringBuffer();
        for (byte element : byteData) {
            String hex = Integer.toHexString(0xff & element);
            if (hex.length() == 1) {
                hexString.append('0');
            }
            hexString.append(hex);
        }
        System.out.println("Hex format : " + hexString.toString());
    }

}
