package net.awired.restmcu.it.resource;

import static net.awired.restmcu.it.builder.NotifBuilder.notif;
import net.awired.ajsl.core.lang.exception.NotFoundException;
import net.awired.ajsl.core.lang.exception.UpdateException;
import net.awired.restmcu.api.domain.line.RestMcuLineNotification;

public class EmulatorLineResource extends LatchLineResource {

    private final EmulatorBoardResource boardResource;

    public EmulatorLineResource(EmulatorBoardResource boardResource) {
        this.boardResource = boardResource;
    }

    @Override
    public void setLineValue(Integer lineId, Float value) throws NotFoundException, UpdateException {
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
