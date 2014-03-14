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
package org.housecream.restmcu.api.filter;

/*
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
*/
