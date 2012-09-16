package net.awired.restmcu.it.wiring;

import net.awired.restmcu.api.domain.board.RestMcuBoard;
import net.awired.restmcu.api.domain.line.RestMcuLine;
import net.awired.restmcu.api.domain.line.RestMcuLineDirection;
import net.awired.restmcu.api.domain.line.RestMcuLineType;

public class TestBoardConfig {

    public static RestMcuLine buildLine(int num) {
        switch (num) {
            case 2: {
                RestMcuLine line = new RestMcuLine();
                line.setDescription("technical desc");
                line.setDirection(RestMcuLineDirection.INPUT);
                line.setType(RestMcuLineType.ANALOG);
                line.setValueMin(0f);
                line.setValueMax(1023f);

                //                line.setName("line2");
                line.setDescription("input analog line");
                //                line.addNotify(new RestMcuLineNotify(RestMcuLineNotifyCondition.inf_or_equal, 0));
                //                line.addNotify(new RestMcuLineNotify(RestMcuLineNotifyCondition.inf_or_equal, 42));
                //                line.setValue(952f);
                return line;
            }
            case 3: {
                RestMcuLine line = new RestMcuLine();

                line.setDescription("out technical desc");
                line.setDirection(RestMcuLineDirection.OUTPUT);
                line.setType(RestMcuLineType.ANALOG);
                line.setValueMin(0f);
                line.setValueMax(254f);
                //                line.setValueStep(2f);

                //                line.setName("line3");
                line.setDescription("output analog line");
                //                line.setValue(60f);
                return line;
            }
            case 4: {
                RestMcuLine line = new RestMcuLine();
                line.setDescription("out technical desc42");
                line.setDirection(RestMcuLineDirection.OUTPUT);
                line.setType(RestMcuLineType.DIGITAL);
                line.setValueMin(0f);
                line.setValueMax(1f);

                //                line.setName("line4");
                line.setDescription("output digital line");
                //                line.setValue(1f);
                return line;
            }
            case 5: {
                RestMcuLine line = new RestMcuLine();

                line.setDescription("in technical desc43");
                line.setDirection(RestMcuLineDirection.INPUT);
                line.setType(RestMcuLineType.DIGITAL);
                line.setValueMin(0f);
                line.setValueMax(1f);

                //                line.setName("line5");
                line.setDescription("input digital line");
                //                line.addNotify(new RestMcuLineNotify(RestMcuLineNotifyCondition.inf_or_equal, 0));
                //                line.addNotify(new RestMcuLineNotify(RestMcuLineNotifyCondition.inf_or_equal, 1));
                //                line.setValue(1f);
                return line;
            }
            default:
                return null;
        }
    }

    public static RestMcuBoard buildBoard() {
        RestMcuBoard device = new RestMcuBoard();
        device.setVersion("1.0");
        device.setSoftware("RestMcu");
        //        device.setNotifyUrl("http://localhost/4242");
        //        device.setNumberOfLine(6);
        device.setHardware("arduino");
        //        device.setName("sample board");
        device.setDescription("genre");
        device.setDescription("little description");
        //        device.setIp("123.456.789.123");
        //        device.setPort(8080);
        device.setMac("df:df:df:df:df:df");
        return device;
    }

}
