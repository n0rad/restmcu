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
