package net.awired.restmcu.it.builder;

import net.awired.restmcu.api.domain.line.RestMcuLine;
import net.awired.restmcu.api.domain.line.RestMcuLineDirection;
import net.awired.restmcu.api.domain.line.RestMcuLineSettings;
import net.awired.restmcu.it.resource.LineInfo;

public class LineInfoBuilder {
    private final Integer id;
    private String name;
    private float value;
    private RestMcuLineDirection direction;

    public static LineInfoBuilder line(Integer id) {
        return new LineInfoBuilder(id);
    }

    public LineInfoBuilder(Integer id) {
        this.id = id;
    }

    public LineInfo build() {
        LineInfo pinInfo = new LineInfo(id);
        pinInfo.setDescription(new RestMcuLine());
        pinInfo.setSettings(new RestMcuLineSettings());
        pinInfo.setValue(value);
        pinInfo.getSettings().setName(name);
        pinInfo.getDescription().setDirection(direction);
        return pinInfo;
    }

    public LineInfoBuilder name(String name) {
        this.name = name;
        return this;
    }

    public LineInfoBuilder value(float value) {
        this.value = value;
        return this;
    }

    public LineInfoBuilder direction(RestMcuLineDirection direction) {
        this.direction = direction;
        return this;
    }

}
