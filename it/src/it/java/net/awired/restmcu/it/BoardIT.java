package net.awired.restmcu.it;

import net.awired.ajsl.core.lang.exception.UpdateException;
import net.awired.restmcu.api.domain.board.RmcuBoard;
import org.junit.Rule;
import org.junit.Test;

public class BoardIT {

    @Rule
    public RmcuTestRule hcc = new RmcuTestRule();

    //    @Test
    //    public void should_reset_data() throws Exception {
    //        RmcuBoard deviceInfo = hcc.getBoardResource().getBoard();
    //        deviceInfo.setDescription("new Description");
    //        hcc.getBoardResource().setBoard(deviceInfo);
    //        hcc.reset();
    //
    //        RmcuBoard deviceInfo2 = hcc.getBoardResource().getBoard();
    //        assertTrue(EqualsBuilder.reflectionEquals(deviceInfo2, TestBoardConfig.buildBoard()));
    //    }
    //
    //    @Test
    //    public void should_get_root_resource() throws Exception {
    //
    //        RmcuBoard deviceInfo = hcc.getBoardResource().getBoard();
    //
    //        assertTrue(EqualsBuilder.reflectionEquals(deviceInfo, TestBoardConfig.buildBoard()));
    //    }
    //
    //    @Test(expected = RmcuUpdateException.class)
    //    public void should_not_update_technical_description() throws Exception {
    //        RmcuBoard deviceInfo = hcc.getBoardResource().getBoard();
    //        deviceInfo.setDescription("new Description");
    //
    //        hcc.getBoardResource().setBoard(deviceInfo);
    //    }
    //
    //    @Test
    //    public void should_update_description() throws Exception {
    //        RmcuBoard board = hcc.getBoardResource().getBoard();
    //        board.setDescription("new Description");
    //
    //        hcc.getBoardResource().setBoard(board);
    //        RmcuBoard updateDevice = hcc.getBoardResource().getBoard();
    //
    //        assertTrue(EqualsBuilder.reflectionEquals(board, TestBoardConfig.buildBoard(), "description"));
    //        assertEquals("new Description", updateDevice.getDescription());
    //        RmcuBoard device2 = hcc.getBoardResource().getBoard();
    //        assertTrue(EqualsBuilder.reflectionEquals(board, TestBoardConfig.buildBoard(), "description"));
    //        assertEquals("new Description", device2.getDescription());
    //    }
    //
    //    @Test
    //    public void should_update_name() throws Exception {
    //        RmcuBoard deviceInfo = hcc.getBoardResource().getBoard();
    //        deviceInfo.setName("new name");
    //
    //        hcc.getBoardResource().setBoard(deviceInfo);
    //        RmcuBoard updateDevice = hcc.getBoardResource().getBoard();
    //
    //        assertTrue(EqualsBuilder.reflectionEquals(deviceInfo, TestBoardConfig.buildBoard(), "name"));
    //        assertEquals("new name", updateDevice.getName());
    //        RmcuBoard device2 = hcc.getBoardResource().getBoard();
    //        assertTrue(EqualsBuilder.reflectionEquals(deviceInfo, TestBoardConfig.buildBoard(), "name"));
    //        assertEquals("new name", device2.getName());
    //    }
    //
    //    @Test
    //    public void should_update_notifyurl() throws Exception {
    //        RmcuBoard deviceInfo = hcc.getBoardResource().getBoard();
    //        deviceInfo.setNotifyUrl("http://localhost:5353");
    //
    //        hcc.getBoardResource().setBoard(deviceInfo);
    //        RmcuBoard updateDevice = hcc.getBoardResource().getBoard();
    //
    //        assertTrue(EqualsBuilder.reflectionEquals(deviceInfo, TestBoardConfig.buildBoard(), "notifyUrl"));
    //        assertEquals("http://localhost:5353", updateDevice.getNotifyUrl());
    //        RmcuBoard device2 = hcc.getBoardResource().getBoard();
    //        assertTrue(EqualsBuilder.reflectionEquals(deviceInfo, TestBoardConfig.buildBoard(), "notifyUrl"));
    //        assertEquals("http://localhost:5353", device2.getNotifyUrl());
    //    }
    //
    //    @Test
    //    public void should_be_able_to_update_all() throws Exception {
    //        RmcuBoard deviceInfo = hcc.getBoardResource().getBoard();
    //        deviceInfo.setNotifyUrl("http://localhost:5353");
    //        deviceInfo.setDescription("desc");
    //        deviceInfo.setName("GFDSQ");
    //
    //        hcc.getBoardResource().setBoard(deviceInfo);
    //        RmcuBoard updateDevice = hcc.getBoardResource().getBoard();
    //
    //        assertTrue(EqualsBuilder.reflectionEquals(deviceInfo, updateDevice));
    //        RmcuBoard device2 = hcc.getBoardResource().getBoard();
    //        assertTrue(EqualsBuilder.reflectionEquals(deviceInfo, device2));
    //    }
    //
    //    @Test(expected = RmcuUpdateException.class)
    //    public void should_not_update_ip() throws Exception {
    //        RmcuBoard deviceInfo = hcc.getBoardResource().getBoard();
    //        deviceInfo.setIp("23456789");
    //
    //        hcc.getBoardResource().setBoard(deviceInfo);
    //    }
    //
    //    @Test(expected = RmcuUpdateException.class)
    //    public void should_not_update_port() throws Exception {
    //        RmcuBoard deviceInfo = hcc.getBoardResource().getBoard();
    //        deviceInfo.setPort(4242);
    //
    //        hcc.getBoardResource().setBoard(deviceInfo);
    //    }
    //
    //    @Test(expected = RmcuUpdateException.class)
    //    public void should_not_update_mac() throws Exception {
    //        RmcuBoard deviceInfo = hcc.getBoardResource().getBoard();
    //        deviceInfo.setMac("ZERTYU");
    //
    //        hcc.getBoardResource().setBoard(deviceInfo);
    //    }
    //
    //    @Test(expected = RmcuUpdateException.class)
    //    public void should_not_update_number_of_pin() throws Exception {
    //        RmcuBoard deviceInfo = hcc.getBoardResource().getBoard();
    //        deviceInfo.setPinIds(ImmutableList.of(42));
    //
    //        hcc.getBoardResource().setBoard(deviceInfo);
    //    }
    //
    //    @Test(expected = RmcuUpdateException.class)
    //    public void should_not_update_software() throws Exception {
    //        RmcuBoard deviceInfo = hcc.getBoardResource().getBoard();
    //        deviceInfo.setSoftware("23456789");
    //
    //        hcc.getBoardResource().setBoard(deviceInfo);
    //    }
    //
    //    @Test(expected = RmcuUpdateException.class)
    //    public void should_not_update_version() throws Exception {
    //        RmcuBoard deviceInfo = hcc.getBoardResource().getBoard();
    //        deviceInfo.setVersion("23456789");
    //
    //        hcc.getBoardResource().setBoard(deviceInfo);
    //    }
    //
    //    @Test(expected = RmcuUpdateException.class)
    //    public void should_not_set_name_to_null() throws Exception {
    //        RmcuBoard deviceInfo = hcc.getBoardResource().getBoard();
    //        deviceInfo.setName(null);
    //
    //        hcc.getBoardResource().setBoard(deviceInfo);
    //    }

    @Test(expected = UpdateException.class)
    public void should_not_set_description_to_null() throws Exception {
        RmcuBoard deviceInfo = hcc.getBoardResource().getBoard();
        deviceInfo.setDescription(null);

        hcc.getBoardResource().setBoard(deviceInfo);
    }
}
