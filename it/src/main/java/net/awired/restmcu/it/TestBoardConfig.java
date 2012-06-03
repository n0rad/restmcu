package net.awired.restmcu.it;

import net.awired.restmcu.api.domain.board.RmcuBoard;
import net.awired.restmcu.api.domain.pin.RmcuPin;
import net.awired.restmcu.api.domain.pin.RmcuPinDirection;
import net.awired.restmcu.api.domain.pin.RmcuPinNotify;
import net.awired.restmcu.api.domain.pin.RmcuPinNotifyCondition;
import net.awired.restmcu.api.domain.pin.RmcuPinType;

public class TestBoardConfig {

    public static RmcuPin buildPin(int num) {
        switch (num) {
            case 2: {
                RmcuPin pin = new RmcuPin();
                pin.setDescription("technical desc");
                pin.setDirection(RmcuPinDirection.INPUT);
                pin.setType(RmcuPinType.ANALOG);
                pin.setValueMin(0f);
                pin.setValueMax(1023f);

                pin.setName("pin2");
                pin.setDescription("input analog pin");
                pin.addNotify(new RmcuPinNotify(RmcuPinNotifyCondition.inf_or_equal, 0));
                pin.addNotify(new RmcuPinNotify(RmcuPinNotifyCondition.inf_or_equal, 42));
                //                pin.setValue(952f);
                return pin;
            }
            case 3: {
                RmcuPin pin = new RmcuPin();

                pin.setDescription("out technical desc");
                pin.setDirection(RmcuPinDirection.OUTPUT);
                pin.setType(RmcuPinType.ANALOG);
                pin.setValueMin(0f);
                pin.setValueMax(254f);
                //                pin.setValueStep(2f);

                pin.setName("pin3");
                pin.setDescription("output analog pin");
                //                pin.setValue(60f);
                return pin;
            }
            case 4: {
                RmcuPin pin = new RmcuPin();
                pin.setDescription("out technical desc42");
                pin.setDirection(RmcuPinDirection.OUTPUT);
                pin.setType(RmcuPinType.DIGITAL);
                pin.setValueMin(0f);
                pin.setValueMax(1f);

                pin.setName("pin4");
                pin.setDescription("output digital pin");
                //                pin.setValue(1f);
                return pin;
            }
            case 5: {
                RmcuPin pin = new RmcuPin();

                pin.setDescription("in technical desc43");
                pin.setDirection(RmcuPinDirection.INPUT);
                pin.setType(RmcuPinType.DIGITAL);
                pin.setValueMin(0f);
                pin.setValueMax(1f);

                pin.setName("pin5");
                pin.setDescription("input digital pin");
                pin.addNotify(new RmcuPinNotify(RmcuPinNotifyCondition.inf_or_equal, 0));
                pin.addNotify(new RmcuPinNotify(RmcuPinNotifyCondition.inf_or_equal, 1));
                //                pin.setValue(1f);
                return pin;
            }
            default:
                return null;
        }
    }

    public static RmcuBoard buildBoard() {
        RmcuBoard device = new RmcuBoard();
        device.setVersion("1.0");
        device.setSoftware("RestMcu");
        device.setNotifyUrl("http://localhost/4242");
        //        device.setNumberOfPin(6);
        device.setHardware("arduino");
        device.setName("sample board");
        device.setDescription("genre");
        device.setDescription("little description");
        device.setIp("123.456.789.123");
        device.setPort(8080);
        device.setMac("df:df:df:df:df:df");
        return device;
    }

}
