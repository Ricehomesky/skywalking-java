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
 *
 */

package org.apache.skywalking.apm.testcase.caffeine.controller;

import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;
import java.util.HashMap;
import java.util.Map;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CaffeineController {

    Cache<Object, Object> caffeine = Caffeine.newBuilder().build();

    @GetMapping("/case/caffeine")
    public void testCase() {
        try {
            Map<String, String> data = new HashMap<>();
            data.put("2", "value-2");

            // put operations
            caffeine.put("1", "value-1");
            caffeine.putAll(data);

            // get operations
            caffeine.get("1", o -> "default");
            caffeine.getIfPresent("1");
            caffeine.getAllPresent(data.keySet());

            // delete operations
            caffeine.invalidate("1");
            caffeine.invalidateAll(data.keySet());
            caffeine.invalidateAll();
        } catch (Exception e) {
            throw e;
        }
    }
}
