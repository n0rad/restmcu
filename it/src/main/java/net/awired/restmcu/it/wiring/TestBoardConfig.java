package net.awired.restmcu.it.wiring;

import net.awired.restmcu.api.domain.board.RestMcuBoard;
import net.awired.restmcu.api.domain.pin.RestMcuPin;
import net.awired.restmcu.api.domain.pin.RestMcuPinDirection;
import net.awired.restmcu.api.domain.pin.RestMcuPinNotify;
import net.awired.restmcu.api.domain.pin.RestMcuPinNotifyCondition;
import net.awired.restmcu.api.domain.pin.RestMcuPinType;

public class TestBoardConfig {

    public static RestMcuPin buildPin(int num) {
        switch (num) {
            case 2: {
                RestMcuPin pin = new RestMcuPin();
                pin.setDescription("technical desc");
                pin.setDirection(RestMcuPinDirection.INPUT);
                pin.setType(RestMcuPinType.ANALOG);
                pin.setValueMin(0f);
                pin.setValueMax(1023f);

                pin.setName("pin2");
                pin.setDescription("input analog pin");
                pin.addNotify(new RestMcuPinNotify(RestMcuPinNotifyCondition.inf_or_equal, 0));
                pin.addNotify(new RestMcuPinNotify(RestMcuPinNotifyCondition.inf_or_equal, 42));
                //                pin.setValue(952f);
                return pin;
            }
            case 3: {
                RestMcuPin pin = new RestMcuPin();

                pin.setDescription("out technical desc");
                pin.setDirection(RestMcuPinDirection.OUTPUT);
                pin.setType(RestMcuPinType.ANALOG);
                pin.setValueMin(0f);
                pin.setValueMax(254f);
                //                pin.setValueStep(2f);

                pin.setName("pin3");
                pin.setDescription("output analog pin");
                //                pin.setValue(60f);
                return pin;
            }
            case 4: {
                RestMcuPin pin = new RestMcuPin();
                pin.setDescription("out technical desc42");
                pin.setDirection(RestMcuPinDirection.OUTPUT);
                pin.setType(RestMcuPinType.DIGITAL);
                pin.setValueMin(0f);
                pin.setValueMax(1f);

                pin.setName("pin4");
                pin.setDescription("output digital pin");
                //                pin.setValue(1f);
                return pin;
            }
            case 5: {
                RestMcuPin pin = new RestMcuPin();

                pin.setDescription("in technical desc43");
                pin.setDirection(RestMcuPinDirection.INPUT);
                pin.setType(RestMcuPinType.DIGITAL);
                pin.setValueMin(0f);
                pin.setValueMax(1f);

                pin.setName("pin5");
                pin.setDescription("input digital pin");
                pin.addNotify(new RestMcuPinNotify(RestMcuPinNotifyCondition.inf_or_equal, 0));
                pin.addNotify(new RestMcuPinNotify(RestMcuPinNotifyCondition.inf_or_equal, 1));
                //                pin.setValue(1f);
                return pin;
            }
            default:
                return null;
        }
    }

    public static RestMcuBoard buildBoard() {
        RestMcuBoard device = new RestMcuBoard();
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
