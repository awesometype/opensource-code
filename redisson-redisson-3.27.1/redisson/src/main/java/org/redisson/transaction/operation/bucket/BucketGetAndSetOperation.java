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
package org.redisson.transaction.operation.bucket;

import java.util.concurrent.TimeUnit;

import org.redisson.RedissonBucket;
import org.redisson.RedissonLock;
import org.redisson.api.RBucket;
import org.redisson.client.codec.Codec;
import org.redisson.command.CommandAsyncExecutor;
import org.redisson.transaction.RedissonTransactionalLock;
import org.redisson.transaction.operation.TransactionalOperation;

/**
 * 
 * @author Nikita Koksharov
 *
 * @param <V> value type
 */
public class BucketGetAndSetOperation<V> extends TransactionalOperation {

    private Object value;
    private String lockName;
    private String transactionId;
    private long timeToLive;
    private TimeUnit timeUnit;

    public BucketGetAndSetOperation(String name, String lockName, Codec codec, Object value, long timeToLive, TimeUnit timeUnit, String transactionId) {
        this(name, lockName, codec, value, transactionId);
        this.timeToLive = timeToLive;
        this.timeUnit = timeUnit;
    }
    
    public BucketGetAndSetOperation(String name, String lockName, Codec codec, Object value, String transactionId) {
        super(name, codec, Thread.currentThread().getId());
        this.value = value;
        this.lockName = lockName;
        this.transactionId = transactionId;
    }

    @Override
    public void commit(CommandAsyncExecutor commandExecutor) {
        RBucket<V> bucket = new RedissonBucket<V>(codec, commandExecutor, name);
        if (timeToLive != 0) {
            bucket.getAndSetAsync((V) value, timeToLive, timeUnit);
        } else {
            bucket.getAndSetAsync((V) value);
        }
        RedissonLock lock = new RedissonTransactionalLock(commandExecutor, lockName, transactionId);
        lock.unlockAsync(getThreadId());
    }

    @Override
    public void rollback(CommandAsyncExecutor commandExecutor) {
        RedissonLock lock = new RedissonTransactionalLock(commandExecutor, lockName, transactionId);
        lock.unlockAsync(getThreadId());
    }
    
    public Object getValue() {
        return value;
    }
    
    public String getLockName() {
        return lockName;
    }

}
