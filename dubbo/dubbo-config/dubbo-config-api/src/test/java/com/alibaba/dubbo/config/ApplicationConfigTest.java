/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.alibaba.dubbo.config;

import com.alibaba.dubbo.common.Constants;
import org.junit.Test;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasEntry;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.sameInstance;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.junit.Assert.assertThat;

public class ApplicationConfigTest {
    @Test
    public void testName() throws Exception {
        ApplicationConfig application = new ApplicationConfig();
        application.setName("app");
        assertThat(application.getName(), equalTo("app"));
        application = new ApplicationConfig("app2");
        assertThat(application.getName(), equalTo("app2"));
        Map<String, String> parameters = new HashMap<String, String>();
        ApplicationConfig.appendParameters(parameters, application);
        assertThat(parameters, hasEntry(Constants.APPLICATION_KEY, "app2"));
    }

    @Test
    public void testVersion() throws Exception {
        ApplicationConfig application = new ApplicationConfig("app");
        application.setVersion("1.0.0");
        assertThat(application.getVersion(), equalTo("1.0.0"));
        Map<String, String> parameters = new HashMap<String, String>();
        ApplicationConfig.appendParameters(parameters, application);
        assertThat(parameters, hasEntry("application.version", "1.0.0"));
    }

    @Test
    public void testOwner() throws Exception {
        ApplicationConfig application = new ApplicationConfig("app");
        application.setOwner("owner");
        assertThat(application.getOwner(), equalTo("owner"));
    }

    @Test
    public void testOrganization() throws Exception {
        ApplicationConfig application = new ApplicationConfig("app");
        application.setOrganization("org");
        assertThat(application.getOrganization(), equalTo("org"));
    }

    @Test
    public void testArchitecture() throws Exception {
        ApplicationConfig application = new ApplicationConfig("app");
        application.setArchitecture("arch");
        assertThat(application.getArchitecture(), equalTo("arch"));
    }

    @Test
    public void testEnvironment1() throws Exception {
        ApplicationConfig application = new ApplicationConfig("app");
        application.setEnvironment("develop");
        assertThat(application.getEnvironment(), equalTo("develop"));
        application.setEnvironment("test");
        assertThat(application.getEnvironment(), equalTo("test"));
        application.setEnvironment("product");
        assertThat(application.getEnvironment(), equalTo("product"));
    }

    @Test(expected = IllegalStateException.class)
    public void testEnvironment2() throws Exception {
        ApplicationConfig application = new ApplicationConfig("app");
        application.setEnvironment("illegal-env");
    }

    @Test
    public void testRegistry() throws Exception {
        ApplicationConfig application = new ApplicationConfig("app");
        RegistryConfig registry = new RegistryConfig();
        application.setRegistry(registry);
        assertThat(application.getRegistry(), sameInstance(registry));
        application.setRegistries(Collections.singletonList(registry));
        assertThat(application.getRegistries(), contains(registry));
        assertThat(application.getRegistries(), hasSize(1));
    }

    @Test
    public void testMonitor() throws Exception {
        ApplicationConfig application = new ApplicationConfig("app");
        application.setMonitor(new MonitorConfig("monitor-addr"));
        assertThat(application.getMonitor().getAddress(), equalTo("monitor-addr"));
        application.setMonitor("monitor-addr");
        assertThat(application.getMonitor().getAddress(), equalTo("monitor-addr"));
    }

    @Test
    public void testLogger() throws Exception {
        ApplicationConfig application = new ApplicationConfig("app");
        application.setLogger("log4j");
        assertThat(application.getLogger(), equalTo("log4j"));
    }

    @Test
    public void testDefault() throws Exception {
        ApplicationConfig application = new ApplicationConfig("app");
        application.setDefault(true);
        assertThat(application.isDefault(), is(true));
    }

    @Test
    public void testDumpDirectory() throws Exception {
        ApplicationConfig application = new ApplicationConfig("app");
        application.setDumpDirectory("/dump");
        assertThat(application.getDumpDirectory(), equalTo("/dump"));
        Map<String, String> parameters = new HashMap<String, String>();
        ApplicationConfig.appendParameters(parameters, application);
        assertThat(parameters, hasEntry(Constants.DUMP_DIRECTORY, "/dump"));
    }

    @Test
    public void testQosEnable() throws Exception {
        ApplicationConfig application = new ApplicationConfig("app");
        application.setQosEnable(true);
        assertThat(application.getQosEnable(), is(true));
        Map<String, String> parameters = new HashMap<String, String>();
        ApplicationConfig.appendParameters(parameters, application);
        assertThat(parameters, hasEntry(Constants.QOS_ENABLE, "true"));
    }

    @Test
    public void testQosPort() throws Exception {
        ApplicationConfig application = new ApplicationConfig("app");
        application.setQosPort(8080);
        assertThat(application.getQosPort(), equalTo(8080));
    }

    @Test
    public void testQosAcceptForeignIp() throws Exception {
        ApplicationConfig application = new ApplicationConfig("app");
        application.setQosAcceptForeignIp(true);
        assertThat(application.getQosAcceptForeignIp(), is(true));
        Map<String, String> parameters = new HashMap<String, String>();
        ApplicationConfig.appendParameters(parameters, application);
        assertThat(parameters, hasEntry(Constants.ACCEPT_FOREIGN_IP, "true"));
    }

    @Test
    public void testParameters() throws Exception {
        ApplicationConfig application = new ApplicationConfig("app");
        application.setQosAcceptForeignIp(true);
        Map<String, String> parameters = new HashMap<String, String>();
        parameters.put("k1", "v1");
        ApplicationConfig.appendParameters(parameters, application);
        assertThat(parameters, hasEntry("k1", "v1"));
        assertThat(parameters, hasEntry(Constants.ACCEPT_FOREIGN_IP, "true"));
    }
}
