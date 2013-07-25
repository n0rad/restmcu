/**
 *
 *     Copyright (C) norad.fr
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
