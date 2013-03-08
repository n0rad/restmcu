package net.awired.restmcu.it.resource;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import net.awired.ajsl.core.lang.exception.NotFoundException;
import net.awired.ajsl.core.lang.exception.UpdateException;
import net.awired.restmcu.api.domain.line.RestMcuLine;
import net.awired.restmcu.api.domain.line.RestMcuLineSettings;
import net.awired.restmcu.api.resource.client.RestMcuLineResource;
import org.apache.commons.lang3.tuple.ImmutablePair;
import org.apache.commons.lang3.tuple.Pair;

public class LatchLineResource implements RestMcuLineResource {

    private Map<Integer, LineInfo> lines = new HashMap<Integer, LineInfo>();

    public LatchLineResource addLine(LineInfo lineInfo) {
        lines.put(lineInfo.getLineId(), lineInfo);
        return this;
    }

    public LineInfo lineInfo(int lineId) {
        return lines.get(lineId);
    }

    public void resetValueLatch(int lineId) {
        lines.get(lineId).setValueLatch(new CountDownLatch(1));
        lines.get(lineId).setDateLatch(null);
    }

    public float awaitLineValue(int lineId) throws InterruptedException {
        LineInfo lineInfo = lines.get(lineId);
        if (!lineInfo.getValueLatch().await(10, TimeUnit.SECONDS)) {
            throw new RuntimeException("Countdown timeout");
        }
        return lineInfo.getValue();
    }

    public Pair<Float, Date> awaitLineValueAndDate(int lineId) throws InterruptedException {
        LineInfo lineInfo = lines.get(lineId);
        if (!lineInfo.getValueLatch().await(10, TimeUnit.SECONDS)) {
            throw new RuntimeException("Countdown timeout");
        }
        return new ImmutablePair<Float, Date>(lineInfo.getValue(), lineInfo.getDateLatch());
    }

    @Override
    public RestMcuLine getLine(Integer lineId) throws NotFoundException {
        LineInfo lineInfo = lines.get(lineId);
        if (lineInfo == null) {
            throw new NotFoundException("line not found" + lineId);
        }
        return lineInfo.getDescription();
    }

    @Override
    public RestMcuLineSettings getLineSettings(Integer lineId) throws NotFoundException, UpdateException {
        LineInfo lineInfo = lines.get(lineId);
        if (lineInfo == null) {
            throw new NotFoundException("line not found" + lineId);
        }
        return lineInfo.getSettings();
    }

    @Override
    public void setLineSettings(Integer lineId, RestMcuLineSettings lineSettings) throws NotFoundException,
            UpdateException {
        LineInfo lineInfo = lines.get(lineId);
        if (lineInfo == null) {
            throw new NotFoundException("line not found" + lineId);
        }

        if (lineSettings.getName() != null) {
            lineInfo.getSettings().setName(lineSettings.getName());
        }

        if (lineSettings.getNotifies() != null) {
            lineInfo.getSettings().setNotifies(lineSettings.getNotifies());
        }
    }

    @Override
    public Float getLineValue(Integer lineId) throws NotFoundException {
        LineInfo lineInfo = lines.get(lineId);
        if (lineInfo == null) {
            throw new NotFoundException("line not found" + lineId);
        }
        return lineInfo.getValue();
    }

    @Override
    public void setLineValue(Integer lineId, Float value) throws NotFoundException, UpdateException {
        LineInfo lineInfo = lines.get(lineId);
        if (lineInfo == null) {
            throw new NotFoundException("line not found" + lineId);
        }
        lineInfo.setValue(value);
        lineInfo.setDateLatch(new Date());
        lineInfo.getValueLatch().countDown();
    }

}
