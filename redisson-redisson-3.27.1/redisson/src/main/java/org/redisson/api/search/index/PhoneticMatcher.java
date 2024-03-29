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
package org.redisson.api.search.index;

/**
 *
 * @author Nikita Koksharov
 *
 */
public enum PhoneticMatcher {

    DM_EN("dm:en"),

    DM_FR("dm:fr"),

    DM_PT("dm:pt"),

    DM_ES("dm:es");

    private final String value;

    PhoneticMatcher(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return value;
    }
}
