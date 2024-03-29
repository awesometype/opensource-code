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
package org.redisson.transaction.operation.map;

import org.redisson.api.RMap;

/**
 * 
 * @author Nikita Koksharov
 *
 */
public class MapReplaceOperation extends MapOperation {

    public MapReplaceOperation() {
    }
    
    public MapReplaceOperation(RMap<?, ?> map, Object key, Object value, Object oldValue, String transactionId, long threadId) {
        super(map, key, value, oldValue, transactionId, threadId);
    }
    
    public MapReplaceOperation(RMap<?, ?> map, Object key, Object value, String transactionId, long threadId) {
        super(map, key, value, transactionId, threadId);
    }

    @Override
    public void commit(RMap<Object, Object> map) {
        if (oldValue != null) {
            map.replaceAsync(key, oldValue, value);
        } else {
            map.replaceAsync(key, value);
        }
    }
    
}
