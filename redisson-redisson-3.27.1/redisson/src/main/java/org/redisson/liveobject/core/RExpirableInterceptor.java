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
package org.redisson.liveobject.core;

import java.lang.reflect.Method;
import net.bytebuddy.implementation.bind.annotation.AllArguments;
import net.bytebuddy.implementation.bind.annotation.FieldValue;
import net.bytebuddy.implementation.bind.annotation.Origin;
import net.bytebuddy.implementation.bind.annotation.RuntimeType;
import org.redisson.api.RExpirable;
import org.redisson.api.RMap;
import org.redisson.liveobject.misc.ClassUtils;

/**
 *
 * @author Rui Gu (https://github.com/jackygurui)
 */
public class RExpirableInterceptor {

    @RuntimeType
    public static Object intercept(
            @Origin Method method,
            @AllArguments Object[] args,
            @FieldValue("liveObjectLiveMap") RMap<?, ?> map
    ) throws Exception {
        Class<?>[] cls = new Class[args.length];
        for (int i = 0; i < args.length; i++) {
            cls[i] = args[i].getClass();
        }
        return ClassUtils.searchForMethod(RExpirable.class, method.getName(), cls).invoke(map, args);
    }
}
