package net.awired.restmcu.stub;

import java.util.HashMap;
import java.util.Map;
import net.awired.ajsl.core.lang.exception.NotFoundException;
import net.awired.ajsl.core.lang.exception.UpdateException;
import net.awired.restmcu.api.domain.board.RestMcuBoard;
import net.awired.restmcu.api.domain.pin.RestMcuPin;
import org.springframework.stereotype.Component;
import com.google.common.base.Strings;

@Component
public class HccContext {

    private RestMcuBoard board;

    Map<Integer, RestMcuPin> pins = new HashMap<Integer, RestMcuPin>();

    public HccContext() {
        //board = DefaultStubDomainHelper.buildDefaultDevice();
        //        for (int i = 0; i < board.getNumberOfPin(); i++) {
        //            pins.put(i, DefaultStubDomainHelper.buildDefaultPin(i));
        //        }
    }

    public RestMcuBoard getBoard() {
        return board;
    }

    public RestMcuPin getPin(int pinId) throws NotFoundException {
        if (pinId < 0 || pinId > pins.size() - 1) {
            throw new NotFoundException("cannot found pin with id " + pinId);
        }
        return pins.get(pinId);
    }

    public void updateDevice(RestMcuBoard board) throws UpdateException {
        if (Strings.isNullOrEmpty(board.getName())) {
            throw new UpdateException("name cannot be empty");
        }
        if (Strings.isNullOrEmpty(board.getDescription())) {
            throw new UpdateException("description cannot be empty");
        }
        if (!board.getDescription().equals(board.getDescription())) {
            throw new UpdateException("technical description is not updatable");
        }
        if (!board.getVersion().equals(board.getVersion())) {
            throw new UpdateException("version is not updatable");
        }
        if (!board.getSoftware().equals(board.getSoftware())) {
            throw new UpdateException("software is not updatable");
        }
        if (!board.getIp().equals(board.getIp())) {
            throw new UpdateException("ip is not updatable");
        }
        if (!board.getPort().equals(board.getPort())) {
            throw new UpdateException("port is not updatable");
        }
        if (!board.getMac().equals(board.getMac())) {
            throw new UpdateException("mac is not updatable");
        }
        //        if (!board.getNumberOfPin().equals(board.getNumberOfPin())) {
        //            throw new HccUpdateException("mac is not updatable");
        //        }
        this.board = board;
    }

    //    public void updatePin(int pinId, HccPinInfo pinInfo) throws HccUpdateException, PinNotFoundException {
    //        HccPin hccPin = pins.get(pinId);
    //        if (hccPin == null) {
    //            throw new PinNotFoundException("cannot found pin with id " + pinId);
    //        }
    //        HccPin description = hccPin.getDescription();
    //        if (description.getDirection() == HccPinDirection.RESERVED
    //                || description.getDirection() == HccPinDirection.NOTUSED) {
    //            throw new HccUpdateException("pin is not updatable");
    //        }
    //
    //        if (Strings.isNullOrEmpty(pinInfo.getName())) {
    //            throw new HccUpdateException("name cannot be empty");
    //        }
    //        if (Strings.isNullOrEmpty(pinInfo.getDescription())) {
    //            throw new HccUpdateException("description cannot be empty");
    //        }
    //
    //        //
    //
    //        if (description.getDirection() == HccPinDirection.OUTPUT) {
    //            if (pinInfo.getStartVal() == null) {
    //                throw new HccUpdateException("start value cannot be null");
    //            }
    //            if (pinInfo.getNotifies() != null) {
    //                throw new HccUpdateException("cannot set notify on output pin");
    //            }
    //            if (pinInfo.getStartVal() > description.getValueMax()) {
    //                throw new HccUpdateException("cannot start with value : " + pinInfo.getStartVal()
    //                        + " superior than max value : " + description.getValueMax());
    //            }
    //            if (pinInfo.getStartVal() < description.getValueMin()) {
    //                throw new HccUpdateException("cannot start with value : " + pinInfo.getStartVal()
    //                        + " inferior than min value : " + description.getValueMin());
    //            }
    //            if (pinInfo.getStartVal() % description.getValueStep() != 0) {
    //                throw new HccUpdateException("cannot set start value : " + pinInfo.getStartVal()
    //                        + " that is not in step of " + description.getValueStep());
    //            }
    //        }
    //        if (description.getDirection() == HccPinDirection.INPUT) {
    //            if (pinInfo.getStartVal() != null) {
    //                throw new HccUpdateException("start value cannot be set for input pin");
    //            }
    //
    //            if (pinInfo.getNotifies() != null) {
    //                for (HccNotify notify : pinInfo.getNotifies()) {
    //                    if (notify.getNotifyValue() > description.getValueMax()) {
    //                        throw new HccUpdateException("cannot notify for value : " + notify.getNotifyValue()
    //                                + " superior than max value : " + description.getValueMax());
    //                    }
    //                    if (notify.getNotifyValue() < description.getValueMin()) {
    //                        throw new HccUpdateException("cannot notify for value : " + notify.getNotifyValue()
    //                                + " inferior than min value : " + description.getValueMin());
    //                    }
    //                }
    //            }
    //        }
    //
    //        this.pins.get(pinId).setInfo(pinInfo);
    //    }
    //
    //    public void setPinValue(int pinId, Float value) throws HccUpdateException, HccPinNotFoundException {
    //        HccPin hccPin = pins.get(pinId);
    //        if (hccPin == null) {
    //            throw new HccPinNotFoundException("cannot found pin with id " + pinId);
    //        }
    //        if (description.getDirection() == HccPinDirection.INPUT) {
    //            throw new HccUpdateException("cannot set value on an input pin");
    //        }
    //
    //        if (value > description.getValueMax()) {
    //            throw new HccUpdateException("cannot set value : " + value + " superior than max value : "
    //                    + description.getValueMax());
    //        }
    //        if (value < description.getValueMin()) {
    //            throw new HccUpdateException("cannot set value : " + value + " inferior than min value : "
    //                    + description.getValueMin());
    //        }
    //        if (value % description.getValueStep() != 0) {
    //            throw new HccUpdateException("cannot set value : " + value + " that is not in step of "
    //                    + description.getValueStep());
    //        }
    //
    //        hccPin.setValue(value);
    //    }
    //
    //    public void setdebugValue(int pinId, Float value) {
    //        HccPin hccPin = this.pins.get(pinId);
    //        Float currentValue = hccPin.getValue();
    //        hccPin.setValue(value);
    //        if (!currentValue.equals(value) && hccPin.getInfo().getNotifies() != null) {
    //            for (HccNotify notify : hccPin.getInfo().getNotifies()) {
    //                if (notify.getNotifyCondition() == HccCondition.inf_or_equal) {
    //                    if (currentValue > notify.getNotifyValue() && value <= notify.getNotifyValue()) {
    //                        notifyPinModification(pinId, notify, value);
    //                    }
    //                } else if (notify.getNotifyCondition() == HccCondition.sup_or_equal) {
    //                    if (currentValue < notify.getNotifyValue() && value >= notify.getNotifyValue()) {
    //                        notifyPinModification(pinId, notify, value);
    //                    }
    //                }
    //            }
    //        }
    //
    //    }
    //
    //    private void notifyPinModification(int pinId, HccNotify notify, Float value) {
    //        HccPinNotify pinNotify = new HccPinNotify();
    //        pinNotify.setCondition(notify);
    //        pinNotify.setPinId(pinId);
    //        pinNotify.setValue(value);
    //        pinNotify.setAddress(board.getIp());
    //
    //        HccNotifyResource notifyResource = JAXRSClientFactory.create(board.getNotifyUrl(), HccNotifyResource.class);
    //        notifyResource.notify(pinNotify);
    //    }
}
