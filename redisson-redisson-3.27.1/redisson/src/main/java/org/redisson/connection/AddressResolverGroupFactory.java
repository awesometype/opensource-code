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
package org.redisson.connection;

import io.netty.channel.socket.DatagramChannel;
import io.netty.channel.socket.SocketChannel;
import io.netty.resolver.AddressResolverGroup;
import io.netty.resolver.dns.DnsServerAddressStreamProvider;

import java.net.InetSocketAddress;

/**
 * Created by hasaadon on 15/02/2018.
 */
public interface AddressResolverGroupFactory {

    AddressResolverGroup<InetSocketAddress> create(Class<? extends DatagramChannel> channelType,
                                                   Class<? extends SocketChannel> socketChannelType,
                                                   DnsServerAddressStreamProvider nameServerProvider);

}
