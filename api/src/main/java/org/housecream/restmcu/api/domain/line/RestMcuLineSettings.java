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
package org.housecream.restmcu.api.domain.line;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "lineSettings")
@XmlAccessorType(XmlAccessType.FIELD)
public class RestMcuLineSettings {

    private String name;
    private List<RestMcuLineNotify> notifies;

    public void addNotify(RestMcuLineNotify notify) {
        if (this.notifies == null) {
            this.notifies = new ArrayList<RestMcuLineNotify>();
        }
        this.notifies.add(notify);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<RestMcuLineNotify> getNotifies() {
        return notifies;
    }

    public void setNotifies(List<RestMcuLineNotify> notifies) {
        this.notifies = notifies;
    }

}
