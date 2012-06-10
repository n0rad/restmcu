package net.awired.restmcu.it;

public class RestMcuContext {

    private static final String BOARD_HOST_DEFAULT = "localhost";
    private static final String BOARD_HOST_PROPERTY_NAME = "board.host";

    private static final String BOARD_PORT_DEFAULT = "8080";
    private static final String BOARD_PORT_PROPERTY_NAME = "board.port";

    private static final String BOARD_PATH_DEFAULT = "/restmcu";
    private static final String BOARD_PATH_PROPERTY_NAME = "board.path";

    public static String getUrl() {
        return "http://" + System.getProperty(BOARD_HOST_PROPERTY_NAME, BOARD_HOST_DEFAULT) //
                + ":" + System.getProperty(BOARD_PORT_PROPERTY_NAME, BOARD_PORT_DEFAULT) //
                + System.getProperty(BOARD_PATH_PROPERTY_NAME, BOARD_PATH_DEFAULT);
    }
}
