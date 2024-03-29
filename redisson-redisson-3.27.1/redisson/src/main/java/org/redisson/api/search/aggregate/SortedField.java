/**
 * Copyright (c) 2013-2024 Nikita Koksharov
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.redisson.api.search.aggregate;

import org.redisson.api.SortOrder;

/**
 *
 * @author Nikita Koksharov
 *
 */
public final class SortedField {

    private final String name;
    private final SortOrder order;

    public SortedField(String name) {
        this(name, SortOrder.ASC);
    }

    public SortedField(String name, SortOrder order) {
        this.name = name;
        this.order = order;
    }

    public String getName() {
        return name;
    }

    public SortOrder getOrder() {
        return order;
    }
}
