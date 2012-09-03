package net.awired.restmcu.it;

public class RestMcuTestContext {

    private static final String BOARD_HOST_DEFAULT = "192.168.42.30";
    private static final String BOARD_HOST_PROPERTY_NAME = "board.host";

    private static final String BOARD_PORT_DEFAULT = "80";
    private static final String BOARD_PORT_PROPERTY_NAME = "board.port";

    private static final String BOARD_PATH_DEFAULT = "/";
    private static final String BOARD_PATH_PROPERTY_NAME = "board.path";

    public static String getUrl() {
        return "http://" + System.getProperty(BOARD_HOST_PROPERTY_NAME, BOARD_HOST_DEFAULT).replaceAll(",", ".") //
                + ":" + System.getProperty(BOARD_PORT_PROPERTY_NAME, BOARD_PORT_DEFAULT) //
                + System.getProperty(BOARD_PATH_PROPERTY_NAME, BOARD_PATH_DEFAULT);
    }

}
