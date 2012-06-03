package net.awired.restmcu.it;

public class RmcuContext {

    private static final String URL_DEFAULT = "http://localhost:8080/restmcu";
    private static final String URL_PROPERTY_NAME = "hcc.url";

    public static String getUrl() {
        return System.getProperty(URL_PROPERTY_NAME, URL_DEFAULT);
    }
}
