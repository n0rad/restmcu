package net.awired.restmcu.it;

import java.util.HashMap;
import java.util.Map;
import net.awired.ajsl.web.resource.mapper.AjslResponseExceptionMapper;
import net.awired.restmcu.api.domain.board.RestMcuBoard;
import net.awired.restmcu.api.domain.pin.RestMcuPin;
import net.awired.restmcu.api.resource.client.RestMcuBoardResource;
import net.awired.restmcu.api.resource.client.RestMcuPinResource;
import net.awired.restmcu.api.resource.test.RestMcuDebugResource;
import org.apache.cxf.jaxrs.client.JAXRSClientFactory;
import org.apache.cxf.jaxrs.provider.JSONProvider;
import org.junit.rules.ExternalResource;
import com.google.common.collect.ImmutableList;

public class RestMcuTestRule extends ExternalResource {

    private RestMcuBoardResource boardResource;

    private RestMcuPinResource pinResource;

    private RestMcuDebugResource debugResource;

    private RestMcuBoard board;

    private Map<Integer, RestMcuPin> pins = new HashMap<Integer, RestMcuPin>();

    public RestMcuTestRule() {
        JSONProvider jsonProvider = new JSONProvider();
        jsonProvider.setSupportUnwrapped(true);
        jsonProvider.setDropRootElement(true);

        AjslResponseExceptionMapper exceptionMapper = new AjslResponseExceptionMapper(jsonProvider);
        ImmutableList<Object> providers = ImmutableList.of(exceptionMapper, jsonProvider);

        pinResource = JAXRSClientFactory.create(RestMcuContext.getUrl(), RestMcuPinResource.class, providers);
        boardResource = JAXRSClientFactory.create(RestMcuContext.getUrl(), RestMcuBoardResource.class, providers);
        debugResource = JAXRSClientFactory.create(RestMcuContext.getUrl(), RestMcuDebugResource.class, providers);
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

    public RestMcuPinResource getPinResource() {
        return pinResource;
    }

    public RestMcuBoardResource getBoardResource() {
        return boardResource;
    }

    public RestMcuDebugResource getDebugResource() {
        return debugResource;
    }

}
