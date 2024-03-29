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
package org.redisson.client.handler;

import org.redisson.client.ChannelName;

import java.util.Objects;

/**
 * 
 * @author Nikita Koksharov
 *
 */
public class PubSubKey {

    private final ChannelName channel;
    private final String operation;
    
    public PubSubKey(ChannelName channel, String operation) {
        super();
        this.channel = channel;
        this.operation = operation;
    }
    
    public ChannelName getChannel() {
        return channel;
    }
    
    public String getOperation() {
        return operation;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PubSubKey pubSubKey = (PubSubKey) o;
        return Objects.equals(channel, pubSubKey.channel) && Objects.equals(operation, pubSubKey.operation);
    }

    @Override
    public int hashCode() {
        return Objects.hash(channel, operation);
    }
}
