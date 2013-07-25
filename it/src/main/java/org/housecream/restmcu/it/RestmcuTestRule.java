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

import org.housecream.restmcu.api.filter.RestMcuSecurityClientInInterceptor;
import org.housecream.restmcu.api.filter.RestMcuSecurityClientOutInterceptor;
import org.housecream.restmcu.api.resource.client.RestMcuBoardResource;
import org.housecream.restmcu.api.resource.client.RestMcuLineResource;
import org.junit.rules.ExternalResource;
import fr.norad.jaxrs.client.server.rest.RestBuilder;

public class RestmcuTestRule extends ExternalResource {

    private RestMcuBoardResource boardResource;

    private RestMcuLineResource lineResource;

    public RestmcuTestRule() {
        this(RestmcuTestContext.getUrl());
    }

    public RestmcuTestRule(String url) {

        RestBuilder context = new RestBuilder();

        context.addInInterceptor(new RestMcuSecurityClientInInterceptor(new RestmcuTestSecurityKey()));
        context.addOutInterceptor(new RestMcuSecurityClientOutInterceptor(new RestmcuTestSecurityKey()));

        lineResource = context.buildClient(RestMcuLineResource.class, url);
        boardResource = context.buildClient(RestMcuBoardResource.class, url);
    }

    @Override
    public void before() throws Throwable {
        reset();
    }

    public void reset() throws Exception {
        //        HccBoard deviceInfo = DefaultITDomainHelper.buildDefaultDevice();
        //        boardResource.setBoard(deviceInfo);

        //        for (int i = 0; i < deviceInfo.getNumberOfLine() - 1; i++) {
        //            HccLineInfo info = DefaultITDomainHelper.buildDefaultLine(i).getInfo();
        //            if (info != null) {
        //                lineResource.setLineInfo(i, info);
        //            }
        //        }
        //
        //        for (int i = 0; i < deviceInfo.getNumberOfLine() - 1; i++) {
        //            HccLine line = DefaultITDomainHelper.buildDefaultLine(i);
        //            if (line.getValue() != null) {
        //                debugResource.setDebugValue(i, line.getValue());
        //            }
        //        }
    }

    public RestMcuLineResource getLineResource() {
        return lineResource;
    }

    public RestMcuBoardResource getBoardResource() {
        return boardResource;
    }

}
