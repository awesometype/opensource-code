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
package org.redisson.api;

import java.util.Collection;
import java.util.Map;
import java.util.Set;

/**
 * Map object with local entry cache support.
 * <p>
 * Each instance maintains local cache to achieve fast read operations.
 * Suitable for maps which used mostly for read operations and network roundtrip delays are undesirable.
 * 
 * @author Nikita Koksharov
 *
 * @param <K> map key
 * @param <V> map value
 */
public interface RLocalCachedMap<K, V> extends RMap<K, V> {
    
    /**
     * Pre-warm the cached entries. Not guaranteed to load ALL values, but statistically
     * will preload approximately all (all if no concurrent mutating activity).
     * Entries are loaded in a batch with size of 10 elements.
     */
    void preloadCache();

    /**
     * Pre-warm the cached entries. Not guaranteed to load ALL values, but statistically
     * will preload approximately all (all if no concurrent mutating activity)
     * Entries are loaded in a batch. Batch size is defined by <code>count</code> param.
     *
     * @param count - size of batch
     */
    void preloadCache(int count);

    /**
     * Clears local cache across all instances
     * 
     * @return void
     */
    RFuture<Void> clearLocalCacheAsync();
    
    /**
     * Clears local cache across all instances
     */
    void clearLocalCache();
    
    /**
     * Returns all keys stored in local cache
     *
     * @return keys
     */
    Set<K> cachedKeySet();

    /**
     * Returns all values stored in local cache
     *
     * @return values
     */
    Collection<V> cachedValues();

    /**
     * Returns all map entries stored in local cache
     *
     * @return entries
     */
    Set<Entry<K, V>> cachedEntrySet();

    /**
     * Returns state of local cache
     *
     * @return map
     */
    Map<K, V> getCachedMap();

    /**
     * Adds local cache event listener
     *
     * @see org.redisson.api.listener.LocalCacheUpdateListener
     * @see org.redisson.api.listener.LocalCacheInvalidateListener
     *
     * @param listener - local cache event listener
     * @return listener id
     */
    @Override
    int addListener(ObjectListener listener);

}