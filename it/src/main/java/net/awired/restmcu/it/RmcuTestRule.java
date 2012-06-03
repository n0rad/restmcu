package net.awired.restmcu.it;

import java.util.HashMap;
import java.util.Map;
import net.awired.ajsl.web.resource.mapper.AjslResponseExceptionMapper;
import net.awired.restmcu.api.domain.board.RmcuBoard;
import net.awired.restmcu.api.domain.pin.RmcuPin;
import net.awired.restmcu.api.resource.client.RmcuBoardResource;
import net.awired.restmcu.api.resource.client.RmcuPinResource;
import net.awired.restmcu.api.resource.test.RmcuDebugResource;
import org.apache.cxf.jaxrs.client.JAXRSClientFactory;
import org.apache.cxf.jaxrs.provider.JSONProvider;
import org.junit.rules.ExternalResource;
import com.google.common.collect.ImmutableList;

public class RmcuTestRule extends ExternalResource {

    private RmcuBoardResource boardResource;

    private RmcuPinResource pinResource;

    private RmcuDebugResource debugResource;

    private RmcuBoard board;

    private Map<Integer, RmcuPin> pins = new HashMap<Integer, RmcuPin>();

    public RmcuTestRule() {
        JSONProvider jsonProvider = new JSONProvider();
        jsonProvider.setSupportUnwrapped(true);
        jsonProvider.setDropRootElement(true);

        AjslResponseExceptionMapper exceptionMapper = new AjslResponseExceptionMapper(jsonProvider);
        ImmutableList<Object> providers = ImmutableList.of(exceptionMapper, jsonProvider);

        pinResource = JAXRSClientFactory.create(RmcuContext.getUrl(), RmcuPinResource.class, providers);
        boardResource = JAXRSClientFactory.create(RmcuContext.getUrl(), RmcuBoardResource.class, providers);
        debugResource = JAXRSClientFactory.create(RmcuContext.getUrl(), RmcuDebugResource.class, providers);
    }

    @Override
    public void before() throws Throwable {
        reset();
    }

    public void reset() throws Exception {
        //        HccBoard deviceInfo = DefaultITDomainHelper.buildDefaultDevice();
        //        boardResource.setBoard(deviceInfo);

        //        for (int i = 0; i < deviceInfo.getNumberOfPin() - 1; i++) {
        //            HccPinInfo info = DefaultITDomainHelper.buildDefaultPin(i).getInfo();
        //            if (info != null) {
        //                pinResource.setPinInfo(i, info);
        //            }
        //        }
        //
        //        for (int i = 0; i < deviceInfo.getNumberOfPin() - 1; i++) {
        //            HccPin pin = DefaultITDomainHelper.buildDefaultPin(i);
        //            if (pin.getValue() != null) {
        //                debugResource.setDebugValue(i, pin.getValue());
        //            }
        //        }
    }

    public RmcuPinResource getPinResource() {
        return pinResource;
    }

    public RmcuBoardResource getBoardResource() {
        return boardResource;
    }

    public RmcuDebugResource getDebugResource() {
        return debugResource;
    }

}
