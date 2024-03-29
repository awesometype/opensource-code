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
package org.redisson.api.redisnode;

import org.redisson.api.RFuture;
import org.redisson.cluster.ClusterSlotRange;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Base Redis Cluster node API interface
 *
 * @author Nikita Koksharov
 *
 */
public interface RedisClusterNodeAsync extends RedisNodeAsync {

    /**
     * Returns cluster information reported by this Redis node
     *
     * @return cluster information
     */
    RFuture<Map<String, String>> clusterInfoAsync();

    /**
     * Returns id of this Redis node
     *
     * @return Redis node Id
     */
    RFuture<String> clusterIdAsync();

    /**
     * Adds slots to this Redis node
     *
     * @param slots slots to add
     * @return void
     */
    RFuture<Void> clusterAddSlotsAsync(int... slots);

    /**
     * Reconfigures this Redis node as replica of Redis node by defined id.
     *
     * @param nodeId Redis node Id
     * @return void
     */
    RFuture<Void> clusterReplicateAsync(String nodeId);

    /**
     * Removes Redis node by defined id from Cluster
     *
     * @param nodeId
     * @return void
     */
    RFuture<Void> clusterForgetAsync(String nodeId);

    /**
     * Removes slots from this Redis node
     *
     * @param slots slots to remove
     * @return void
     */
    RFuture<Void> clusterDeleteSlotsAsync(int... slots);

    /**
     * Counts keys in defined slot
     *
     * @param slot slot
     * @return keys amount
     */
    RFuture<Long> clusterCountKeysInSlotAsync(int slot);

    /**
     * Returns keys in defines slot limited by count
     *
     * @param slot slot
     * @param count limits keys amount
     * @return keys
     */
    RFuture<List<String>> clusterGetKeysInSlotAsync(int slot, int count);

    /**
     * Sets slot to this Redis node according to defined command
     *
     * @param slot slot
     * @param command slot command
     * @return void
     */
    RFuture<Void> clusterSetSlotAsync(int slot, SetSlotCommand command);

    /**
     * Sets slot to this Redis node according to defined command
     *
     * @param slot slot
     * @param command slot command
     * @param nodeId Redis node id
     * @return void
     */
    RFuture<Void> clusterSetSlotAsync(int slot, SetSlotCommand command, String nodeId);

    /**
     * Joins Redis node by the defined address to Cluster
     * <p>
     * Address example: <code>redis://127.0.0.1:9233</code>
     *
     * @param address Redis node address
     * @return void
     */
    RFuture<Void> clusterMeetAsync(String address);

    /**
     * Returns number of failure reports for Redis node by defined id
     *
     * @param nodeId Redis node id
     * @return amount of failure reports
     */
    RFuture<Long> clusterCountFailureReportsAsync(String nodeId);

    /**
     * Removes all slots from this Redis node
     * @return void
     */
    RFuture<Void> clusterFlushSlotsAsync();

    /**
     * Return Redis Cluster slots mapped to Redis nodes
     *
     * @return slots mapping
     */
    RFuture<Map<ClusterSlotRange, Set<String>>> clusterSlotsAsync();

}
