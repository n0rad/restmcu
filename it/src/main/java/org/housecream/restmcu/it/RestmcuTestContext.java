/**
 *
 *     Copyright (C) Housecream.org
 *
 *     This program is free software: you can redistribute it and/or modify
 *     it under the terms of the GNU Affero General Public License as published by
 *     the Free Software Foundation, either version 3 of the License, or any later version.
 *
 *     This program is distributed in the hope that it will be useful,
 *     but WITHOUT ANY WARRANTY; without even the implied warranty of
 *     MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *     GNU Affero General Public License for more details.
 *
 *     You should have received a copy of the GNU Affero General Public License
 *     along with this program.  If not, see <http://www.gnu.org/licenses/>
 */
package org.housecream.restmcu.it;

public class RestmcuTestContext {

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
