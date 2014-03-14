package org.housecream.restmcu.it;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import java.math.BigInteger;
import java.security.SecureRandom;

import org.housecream.restmcu.api.RestMcuUpdateException;
import org.housecream.restmcu.api.domain.board.RestMcuBoardSettings;
import org.junit.Ignore;
import org.junit.Rule;
import org.junit.Test;
import fr.norad.core.lang.exception.UpdateException;

public class BoardSettingsIT {

    @Rule
    public RestmcuTestRule restmcu = new RestmcuTestRule();

    @Test
    public void should_update_settings_with_same() throws Exception {
        RestMcuBoardSettings boardSettings = restmcu.getBoardResource().getBoardSettings();
        restmcu.getBoardResource().setBoardSettings(boardSettings);
        restmcu.getBoardResource().getBoardSettings();
    }

    @Test
    public void should_update_name() throws Exception {
        String name = new BigInteger(30, new SecureRandom()).toString(5);
        RestMcuBoardSettings boardSettings = new RestMcuBoardSettings();
        boardSettings.setName(name);
        restmcu.getBoardResource().setBoardSettings(boardSettings);
        assertEquals(name, restmcu.getBoardResource().getBoardSettings().getName());
    }

    @Test(expected = UpdateException.class)
    public void should_update_notify_with_http() throws Exception {
        String notifyUrl = new BigInteger(30, new SecureRandom()).toString(5);
        RestMcuBoardSettings boardSettings = new RestMcuBoardSettings();
        boardSettings.setNotifyUrl(notifyUrl);
        restmcu.getBoardResource().setBoardSettings(boardSettings);
    }

    @Test
    public void should_update_notify() throws Exception {
        String notifyUrl = "http://127.0.0.1/genre/style";
        RestMcuBoardSettings boardSettings = new RestMcuBoardSettings();
        boardSettings.setNotifyUrl(notifyUrl);
        restmcu.getBoardResource().setBoardSettings(boardSettings);
        assertEquals(notifyUrl, restmcu.getBoardResource().getBoardSettings().getNotifyUrl());
    }

    @Test
    public void should_not_update_notify_on_too_long() throws Exception {
        String oldNotifyUrl = restmcu.getBoardResource().getBoardSettings().getNotifyUrl();
        String notifyUrl = "http://127.0.0.1/genre/styledfkldfmldkfmldskfmdskfmdsfmskdmfksdmfsmfgpgkpkpkfdpbkfdpbkfdp";
        RestMcuBoardSettings boardSettings = new RestMcuBoardSettings();
        boardSettings.setNotifyUrl(notifyUrl);
        boolean exceptionflag = false;
        try {
            restmcu.getBoardResource().setBoardSettings(boardSettings);
        } catch (RestMcuUpdateException e) {
            exceptionflag = true;
        }
        assertTrue(exceptionflag);
        assertEquals(oldNotifyUrl, restmcu.getBoardResource().getBoardSettings().getNotifyUrl());
    }

    @Test
    public void should_not_update_name_when_too_long() throws Exception {
        String oldName = restmcu.getBoardResource().getBoardSettings().getName();
        RestMcuBoardSettings boardSettings = new RestMcuBoardSettings();
        boardSettings.setName("AZERTYUIOPMKLKJHGFDSQWXCVBN");
        boolean exceptionflag = false;
        try {
            restmcu.getBoardResource().setBoardSettings(boardSettings);
        } catch (RestMcuUpdateException e) {
            exceptionflag = true;
        }
        assertTrue(exceptionflag);
        assertEquals(oldName, restmcu.getBoardResource().getBoardSettings().getName());
    }

    @Test
    @Ignore
    // TODO complexe test that change the ip and then go back
    public void should_update_ip() throws Exception {
        RestMcuBoardSettings boardSettings = new RestMcuBoardSettings();
        boardSettings.setIp("192.168.32.30");
        restmcu.getBoardResource().setBoardSettings(boardSettings);
    }

    @Test
    @Ignore
    // TODO complexe test that change the port and then go back
    public void should_update_port() throws Exception {
        RestMcuBoardSettings boardSettings = new RestMcuBoardSettings();
        boardSettings.setPort(81);
        restmcu.getBoardResource().setBoardSettings(boardSettings);
    }

}
