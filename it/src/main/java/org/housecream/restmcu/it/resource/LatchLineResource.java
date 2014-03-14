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

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import org.apache.commons.lang3.tuple.ImmutablePair;
import org.apache.commons.lang3.tuple.Pair;
import org.housecream.restmcu.api.LineNotFoundException;
import org.housecream.restmcu.api.RestMcuUpdateException;
import org.housecream.restmcu.api.domain.line.RestMcuLine;
import org.housecream.restmcu.api.domain.line.RestMcuLineSettings;
import org.housecream.restmcu.api.resource.client.RestMcuLineResource;
import fr.norad.core.lang.exception.NotFoundException;
import fr.norad.core.lang.exception.UpdateException;

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
    public RestMcuLine getLine(Integer lineId) throws LineNotFoundException {
        LineInfo lineInfo = lines.get(lineId);
        if (lineInfo == null) {
            throw new LineNotFoundException("line not found" + lineId);
        }
        return lineInfo.getDescription();
    }

    @Override
    public RestMcuLineSettings getLineSettings(Integer lineId) throws LineNotFoundException, RestMcuUpdateException {
        LineInfo lineInfo = lines.get(lineId);
        if (lineInfo == null) {
            throw new LineNotFoundException("line not found" + lineId);
        }
        return lineInfo.getSettings();
    }

    @Override
    public void setLineSettings(Integer lineId, RestMcuLineSettings lineSettings) throws LineNotFoundException,
            RestMcuUpdateException {
        LineInfo lineInfo = lines.get(lineId);
        if (lineInfo == null) {
            throw new LineNotFoundException("line not found" + lineId);
        }

        if (lineSettings.getName() != null) {
            lineInfo.getSettings().setName(lineSettings.getName());
        }

        if (lineSettings.getNotifies() != null) {
            lineInfo.getSettings().setNotifies(lineSettings.getNotifies());
        }
    }

    @Override
    public Float getLineValue(Integer lineId) throws LineNotFoundException {
        LineInfo lineInfo = lines.get(lineId);
        if (lineInfo == null) {
            throw new LineNotFoundException("line not found" + lineId);
        }
        return lineInfo.getValue();
    }

    @Override
    public void setLineValue(Integer lineId, Float value) throws LineNotFoundException, RestMcuUpdateException {
        LineInfo lineInfo = lines.get(lineId);
        if (lineInfo == null) {
            throw new LineNotFoundException("line not found" + lineId);
        }
        lineInfo.setValue(value);
        lineInfo.setDateLatch(new Date());
        lineInfo.getValueLatch().countDown();
    }

}
