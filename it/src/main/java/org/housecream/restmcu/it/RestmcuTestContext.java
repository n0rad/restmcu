/**
 *
 *     Copyright (C) norad.fr
 *
 *     Licensed under the Apache License, Version 2.0 (the "License");
 *     you may not use this file except in compliance with the License.
 *     You may obtain a copy of the License at
 *
 *             http://www.apache.org/licenses/LICENSE-2.0
 *
 *     Unless required by applicable law or agreed to in writing, software
 *     distributed under the License is distributed on an "AS IS" BASIS,
 *     WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *     See the License for the specific language governing permissions and
 *     limitations under the License.
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
