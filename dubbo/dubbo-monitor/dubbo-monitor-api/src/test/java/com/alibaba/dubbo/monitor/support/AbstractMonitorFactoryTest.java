/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.alibaba.dubbo.monitor.support;

import com.alibaba.dubbo.common.URL;
import com.alibaba.dubbo.common.utils.NetUtils;
import com.alibaba.dubbo.monitor.Monitor;
import com.alibaba.dubbo.monitor.MonitorFactory;

import org.junit.Assert;
import org.junit.Test;

import java.util.List;

/**
 * AbstractMonitorFactoryTest
 */
public class AbstractMonitorFactoryTest {

    private MonitorFactory monitorFactory = new AbstractMonitorFactory() {

        protected Monitor createMonitor(final URL url) {
            return new Monitor() {

                public URL getUrl() {
                    return url;
                }

                @Override
                public boolean isAvailable() {
                    return true;
                }

                @Override
                public void destroy() {
                }

                public void collect(URL statistics) {
                }

                public List<URL> lookup(URL query) {
                    return null;
                }

            };
        }
    };

    @Test
    public void testMonitorFactoryCache() throws Exception {
        URL url = URL.valueOf("dubbo://" + NetUtils.getLocalAddress().getHostAddress() + ":2233");
        Monitor monitor1 = monitorFactory.getMonitor(url);
        Monitor monitor2 = monitorFactory.getMonitor(url);
        if (monitor1 == null || monitor2 == null) {
            Thread.sleep(2000);
            monitor1 = monitorFactory.getMonitor(url);
            monitor2 = monitorFactory.getMonitor(url);
        }
        Assert.assertEquals(monitor1, monitor2);
    }

    @Test
    public void testMonitorFactoryIpCache() throws Exception {
        URL url = URL.valueOf("dubbo://" + NetUtils.getLocalAddress().getHostName() + ":2233");
        Monitor monitor1 = monitorFactory.getMonitor(url);
        Monitor monitor2 = monitorFactory.getMonitor(url);
        if (monitor1 == null || monitor2 == null) {
            Thread.sleep(2000);
            monitor1 = monitorFactory.getMonitor(url);
            monitor2 = monitorFactory.getMonitor(url);
        }
        Assert.assertEquals(monitor1, monitor2);
    }

    @Test
    public void testMonitorFactoryGroupCache() throws Exception {
        URL url1 = URL.valueOf("dubbo://" + NetUtils.getLocalHost() + ":2233?group=aaa");
        URL url2 = URL.valueOf("dubbo://" + NetUtils.getLocalHost() + ":2233?group=bbb");
        Monitor monitor1 = monitorFactory.getMonitor(url1);
        Monitor monitor2 = monitorFactory.getMonitor(url2);
        if (monitor1 == null || monitor2 == null) {
            Thread.sleep(2000);
            monitor1 = monitorFactory.getMonitor(url1);
            monitor2 = monitorFactory.getMonitor(url2);
        }
        Assert.assertNotSame(monitor1, monitor2);
    }

}
