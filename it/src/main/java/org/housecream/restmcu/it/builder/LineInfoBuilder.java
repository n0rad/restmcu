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
package org.housecream.restmcu.it.builder;

import org.housecream.restmcu.api.domain.line.RestMcuLine;
import org.housecream.restmcu.api.domain.line.RestMcuLineDirection;
import org.housecream.restmcu.api.domain.line.RestMcuLineSettings;
import org.housecream.restmcu.it.resource.LineInfo;

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
