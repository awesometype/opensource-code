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

package com.alibaba.dubbo.common.status.support;

import com.alibaba.dubbo.common.logger.Logger;
import com.alibaba.dubbo.common.logger.LoggerFactory;
import com.alibaba.dubbo.common.status.Status;
import org.junit.Test;

import static com.alibaba.dubbo.common.status.Status.Level.OK;
import static com.alibaba.dubbo.common.status.Status.Level.WARN;
import static org.hamcrest.CoreMatchers.anyOf;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class MemoryStatusCheckerTest {
    private static final Logger logger = LoggerFactory.getLogger(MemoryStatusCheckerTest.class);

    @Test
    public void test() throws Exception {
        MemoryStatusChecker statusChecker = new MemoryStatusChecker();
        Status status = statusChecker.check();
        assertThat(status.getLevel(), anyOf(is(OK), is(WARN)));
        logger.info("memory status level: " + status.getLevel());
        logger.info("memory status message: " + status.getMessage());
    }
}
