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
package org.housecream.restmcu.it.resource;

import static org.housecream.restmcu.it.builder.NotifBuilder.notif;

import org.housecream.restmcu.api.LineNotFoundException;
import org.housecream.restmcu.api.RestMcuUpdateException;
import org.housecream.restmcu.api.domain.line.RestMcuLineNotification;
import fr.norad.core.lang.exception.NotFoundException;
import fr.norad.core.lang.exception.UpdateException;

public class EmulatorLineResource extends LatchLineResource {

    private final EmulatorBoardResource boardResource;

    public EmulatorLineResource(EmulatorBoardResource boardResource) {
        this.boardResource = boardResource;
    }

    @Override
    public void setLineValue(Integer lineId, Float value) throws LineNotFoundException, RestMcuUpdateException {
        Float oldValue = lineInfo(lineId).getValue();
        super.setLineValue(lineId, value);
        notifyChange(lineId, oldValue);
    }

    private void notifyChange(Integer lineId, Float oldValue) {
        String source = boardResource.getBoardSettings().getIp() + ":" + boardResource.getBoardSettings().getPort();
        RestMcuLineNotification notif = notif().lineId(lineId).oldVal(oldValue).val(lineInfo(lineId).getValue())
                .build();
        notif.setSource(source);
        boardResource.sendNotif(notif);
    }
}
