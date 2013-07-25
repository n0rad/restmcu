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
