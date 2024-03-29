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
package org.redisson.client;

import org.redisson.misc.RedisURI;

/**
 * 
 * @author Nikita Koksharov
 *
 */
public class RedisAskException extends RedisRedirectException {

    private static final long serialVersionUID = -6969734163155547631L;

    public RedisAskException(int slot, RedisURI url) {
        super(slot, url);
    }

}
