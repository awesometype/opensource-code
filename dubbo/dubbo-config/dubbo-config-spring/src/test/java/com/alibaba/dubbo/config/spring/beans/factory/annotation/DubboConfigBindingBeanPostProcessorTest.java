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
package com.alibaba.dubbo.config.spring.beans.factory.annotation;

import com.alibaba.dubbo.config.ApplicationConfig;
import com.alibaba.dubbo.config.spring.context.config.NamePropertyDefaultValueDubboConfigBeanCustomizer;
import com.alibaba.dubbo.config.spring.context.properties.DefaultDubboConfigBinder;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * {@link DubboConfigBindingBeanPostProcessor}
 */
@RunWith(SpringRunner.class)
@ContextConfiguration(classes = {
        DefaultDubboConfigBinder.class,
        NamePropertyDefaultValueDubboConfigBeanCustomizer.class,
        DubboConfigBindingBeanPostProcessorTest.class
})
@TestPropertySource(properties = {
        "dubbo.application.id = dubbo-demo-application",
        "dubbo.application.owner = mercyblitz",
        "dubbo.application.organization = Apache",

})
public class DubboConfigBindingBeanPostProcessorTest {

    @Bean("dubbo-demo-application")
    public ApplicationConfig applicationConfig() {
        return new ApplicationConfig();
    }

    @Bean
    public DubboConfigBindingBeanPostProcessor bindingBeanPostProcessor() {
        return new DubboConfigBindingBeanPostProcessor("dubbo.application", "dubbo-demo-application");
    }

    @Autowired
    private ApplicationContext applicationContext;

    @Test
    public void test() {

        ApplicationConfig applicationConfig = applicationContext.getBean(ApplicationConfig.class);

        Assert.assertEquals("dubbo-demo-application", applicationConfig.getName());
        Assert.assertEquals("mercyblitz", applicationConfig.getOwner());
        Assert.assertEquals("Apache", applicationConfig.getOrganization());
    }
}
