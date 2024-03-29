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
package org.redisson.api;

import reactor.core.publisher.Mono;

import java.util.List;

/**
 * Interface for Redis Function feature
 *
 * @author Nikita Koksharov
 *
 */
public interface RFunctionReactive {

    /**
     * Deletes library. Error is thrown if library doesn't exist.
     *
     * @param libraryName library name
     */
    Mono<Void> delete(String libraryName);

    /**
     * Returns serialized state of all libraries.
     *
     * @return serialized state
     */
    Mono<byte[]> dump();

    /**
     * Deletes all libraries.
     *
     */
    Mono<Void> flush();

    /**
     * Kills currently executed functions.
     * Applied only to functions which don't modify data.
     *
     */
    Mono<Void> kill();

    /**
     * Returns information about libraries and functions per each.
     *
     * @return list of libraries
     */
    Mono<List<FunctionLibrary>> list();

    /**
     * Returns information about libraries and functions per each by name pattern.
     * <p>
     *  Supported glob-style patterns:
     *    h?llo matches hello, hallo and hxllo
     *    h*llo matches hllo and heeeello
     *    h[ae]llo matches hello and hallo, but not hillo
     *
     * @param namePattern name pattern
     * @return list of libraries
     */
    Mono<List<FunctionLibrary>> list(String namePattern);

    /**
     * Loads a library. Error is thrown if library already exists.
     *
     * @param libraryName library name
     * @param code function code
     */
    Mono<Void> load(String libraryName, String code);

    /**
     * Loads a library and overwrites existing library.
     *
     * @param libraryName library name
     * @param code function code
     */
    Mono<Void> loadAndReplace(String libraryName, String code);

    /**
     * Restores libraries using their state returned by {@link #dump()} method.
     * Restored libraries are appended to the existing libraries and throws error in case of collision.
     *
     * @param payload serialized state
     */
    Mono<Void> restore(byte[] payload);

    /**
     * Restores libraries using their state returned by {@link #dump()} method.
     * Restored libraries are appended to the existing libraries.
     *
     * @param payload serialized state
     */
    Mono<Void> restoreAndReplace(byte[] payload);

    /**
     * Restores libraries using their state returned by {@link #dump()} method.
     * Deletes all existing libraries before restoring.
     *
     * @param payload serialized state
     */
    Mono<Void> restoreAfterFlush(byte[] payload);

    /**
     * Returns information about currently running
     * Redis function and available execution engines.
     *
     * @return function information
     */
    Mono<FunctionStats> stats();

    /**
     * Executes function
     *
     * @param <R>        - type of result
     * @param key        - used to locate Redis node in Cluster
     * @param mode       - execution mode
     * @param name       - function name
     * @param returnType - return type
     * @param keys       - keys available through KEYS param in script
     * @param values     - values available through VALUES param in script
     * @return result object
     */
    <R> Mono<R> call(String key, FunctionMode mode, String name, FunctionResult returnType, List<Object> keys, Object... values);

    /**
     * Executes function
     *
     * @param <R>        - type of result
     * @param mode       - execution mode
     * @param name       - function name
     * @param returnType - return type
     * @param keys       - keys available through KEYS param in script
     * @param values     - values available through VALUES param in script
     * @return result object
     */
    <R> Mono<R> call(FunctionMode mode, String name, FunctionResult returnType, List<Object> keys, Object... values);

    /**
     * Executes function
     *
     * @param <R>        - type of result
     * @param mode       - execution mode
     * @param name       - function name
     * @param returnType - return type
     * @return result object
     */
    <R> Mono<R> call(FunctionMode mode, String name, FunctionResult returnType);

}
