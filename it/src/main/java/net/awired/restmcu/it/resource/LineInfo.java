package net.awired.restmcu.it.resource;

import java.util.Date;
import java.util.concurrent.CountDownLatch;
import net.awired.restmcu.api.domain.line.RestMcuLine;
import net.awired.restmcu.api.domain.line.RestMcuLineSettings;

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
