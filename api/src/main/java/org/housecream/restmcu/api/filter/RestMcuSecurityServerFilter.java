/**
 *
 *     Copyright (C) Awired.net
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
package org.housecream.restmcu.api.filter;

import java.lang.reflect.Method;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.Provider;
import org.apache.cxf.jaxrs.ext.RequestHandler;
import org.apache.cxf.jaxrs.ext.ResponseHandler;
import org.apache.cxf.jaxrs.model.ClassResourceInfo;
import org.apache.cxf.jaxrs.model.OperationResourceInfo;
import org.apache.cxf.message.Message;
import org.housecream.restmcu.api.resource.server.RestMcuNotifyResource;

@Provider
public class RestMcuSecurityServerFilter implements RequestHandler, ResponseHandler {

    private RestMcuSecurityContext restMcuSecurityContext;

    public RestMcuSecurityServerFilter(RestMcuSecurityKey key) {
        restMcuSecurityContext = new RestMcuSecurityContext(key);
    }

    @Override
    public Response handleResponse(Message message, OperationResourceInfo opResourceInfo, Response response) {
        try {
            long time = System.currentTimeMillis() / 1000L;
            response.getMetadata().add("Hmac-Time", time);
            response.getMetadata().add("Hmac-Hash", restMcuSecurityContext.buildHash(time, message));
        } catch (Exception e) {
            return Response.status(Status.INTERNAL_SERVER_ERROR).build();
        }

        return response;
    }

    private boolean isSecurityNeeded(Message message) {
        Method method = (Method) message.get("org.apache.cxf.resource.method");
        if (method == null || !RestMcuNotifyResource.class.isAssignableFrom(method.getDeclaringClass())) {
            return false;
        }
        boolean isTimeCall = message.get(Message.PATH_INFO).equals("/time");
        if (isTimeCall) {
            return false;
        }
        return true;
    }

    @Override
    public Response handleRequest(Message message, ClassResourceInfo classResourceInfo) {
        if (isSecurityNeeded(message)) {
            restMcuSecurityContext.checkSecurity(message);
        }
        return null;
    }

}
