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
import java.util.concurrent.CountDownLatch;
import org.housecream.restmcu.api.domain.line.RestMcuLine;
import org.housecream.restmcu.api.domain.line.RestMcuLineSettings;

public class LineInfo {
    private final Integer lineId;
    private RestMcuLineSettings settings;
    private RestMcuLine description;
    private float value;
    private Date dateLatch;
    private CountDownLatch valueLatch = new CountDownLatch(1);

    public LineInfo(Integer lineId) {
        this.lineId = lineId;
    }

    public Integer getLineId() {
        return lineId;
    }

    public RestMcuLineSettings getSettings() {
        return settings;
    }

    public void setSettings(RestMcuLineSettings settings) {
        this.settings = settings;
    }

    public RestMcuLine getDescription() {
        return description;
    }

    public void setDescription(RestMcuLine description) {
        this.description = description;
    }

    public float getValue() {
        return value;
    }

    public void setValue(float value) {
        this.value = value;
    }

    public Date getDateLatch() {
        return dateLatch;
    }

    public void setDateLatch(Date dateLatch) {
        this.dateLatch = dateLatch;
    }

    public CountDownLatch getValueLatch() {
        return valueLatch;
    }

    public void setValueLatch(CountDownLatch valueLatch) {
        this.valueLatch = valueLatch;
    }
}
