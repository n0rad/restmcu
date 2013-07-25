/**
 *
 *     Copyright (C) Awired.net
 *
 *     Licensed under the Apache License, Version 2.0 (the "License");
 *     you may not use this file except in compliance with the License.
 *     You may obtain a copy of the License at
 *
 *             http://www.apache.org/licenses/LICENSE-2.0
 *
 *     Unless required by applicable law or agreed to in writing, software
 *     distributed under the License is distributed on an "AS IS" BASIS,
 *     WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *     See the License for the specific language governing permissions and
 *     limitations under the License.
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
