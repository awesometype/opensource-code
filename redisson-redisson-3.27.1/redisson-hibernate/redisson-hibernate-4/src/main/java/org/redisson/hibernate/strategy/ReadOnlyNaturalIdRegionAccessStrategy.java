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
package org.redisson.hibernate.strategy;

import org.hibernate.cache.CacheException;
import org.hibernate.cache.spi.GeneralDataRegion;
import org.hibernate.cache.spi.NaturalIdRegion;
import org.hibernate.cache.spi.access.NaturalIdRegionAccessStrategy;
import org.hibernate.cache.spi.access.SoftLock;
import org.hibernate.cfg.Settings;

/**
 * 
 * @author Nikita Koksharov
 *
 */
public class ReadOnlyNaturalIdRegionAccessStrategy extends BaseRegionAccessStrategy implements NaturalIdRegionAccessStrategy {

    public ReadOnlyNaturalIdRegionAccessStrategy(Settings settings, GeneralDataRegion region) {
        super(settings, region);
    }

    @Override
    public Object get(Object key, long txTimestamp) throws CacheException {
        return region.get(key);
    }

    @Override
    public boolean putFromLoad(Object key, Object value, long txTimestamp, Object version, boolean minimalPutOverride)
            throws CacheException {
        if (minimalPutOverride && region.contains(key)) {
            return false;
        }

        region.put(key, value);
        return true;
    }

    @Override
    public SoftLock lockItem(Object key, Object version) throws CacheException {
        return null;
    }

    @Override
    public void unlockItem(Object key, SoftLock lock) throws CacheException {
        evict(key);
    }

    @Override
    public NaturalIdRegion getRegion() {
        return (NaturalIdRegion) region;
    }

    @Override
    public boolean insert(Object key, Object value) throws CacheException {
        return false;
    }

    @Override
    public boolean afterInsert(Object key, Object value) throws CacheException {
        region.put(key, value);
        return true;
    }

    @Override
    public boolean update(Object key, Object value) throws CacheException {
        throw new UnsupportedOperationException("Unable to update read-only object");
    }

    @Override
    public boolean afterUpdate(Object key, Object value, SoftLock lock) throws CacheException {
        throw new UnsupportedOperationException("Unable to update read-only object");
    }

}
