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
package com.alibaba.dubbo.validation.support.jvalidation;

import com.alibaba.dubbo.common.URL;
import com.alibaba.dubbo.validation.support.jvalidation.mock.ValidationParameter;
import org.junit.Test;

import javax.validation.ConstraintViolationException;

public class JValidatorTest {
    @Test(expected = NoSuchMethodException.class)
    public void testItWithNonExistMethod() throws Exception {
        URL url = URL.valueOf("test://test:11/com.alibaba.dubbo.validation.support.jvalidation.mock.JValidatorTestTarget");
        JValidator jValidator = new JValidator(url);
        jValidator.validate("nonExistingMethod", new Class<?>[]{String.class}, new Object[]{"arg1"});
    }

    @Test
    public void testItWithExistMethod() throws Exception {
        URL url = URL.valueOf("test://test:11/com.alibaba.dubbo.validation.support.jvalidation.mock.JValidatorTestTarget");
        JValidator jValidator = new JValidator(url);
        jValidator.validate("someMethod1", new Class<?>[]{String.class}, new Object[]{"anything"});
    }

    @Test(expected = ConstraintViolationException.class)
    public void testItWhenItViolatedConstraint() throws Exception {
        URL url = URL.valueOf("test://test:11/com.alibaba.dubbo.validation.support.jvalidation.mock.JValidatorTestTarget");
        JValidator jValidator = new JValidator(url);
        jValidator.validate("someMethod2", new Class<?>[]{ValidationParameter.class}, new Object[]{new ValidationParameter()});
    }

    @Test
    public void testItWhenItMeetsConstraint() throws Exception {
        URL url = URL.valueOf("test://test:11/com.alibaba.dubbo.validation.support.jvalidation.mock.JValidatorTestTarget");
        JValidator jValidator = new JValidator(url);
        jValidator.validate("someMethod2", new Class<?>[]{ValidationParameter.class}, new Object[]{new ValidationParameter("NotBeNull")});
    }
}