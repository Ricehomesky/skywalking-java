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

package org.apache.skywalking.apm.agent.core.context.tag;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * The span tags are supported by sky-walking engine. As default, all tags will be stored, but these ones have
 * particular meanings.
 * <p>
 */
public final class Tags {
    private static final Map<String, StringTag> TAG_PROTOTYPES = new ConcurrentHashMap<>();

    private Tags() {
    }

    /**
     * URL records the url of the incoming request.
     */
    public static final StringTag URL = new StringTag(1, "url");

    /**
     * STATUS_CODE records the http status code of the response.
     */
    public static final IntegerTag HTTP_RESPONSE_STATUS_CODE = new IntegerTag(2, "http.status_code", true);

    /**
     * DB_TYPE records database type, such as sql, cassandra and so on.
     */
    public static final StringTag DB_TYPE = new StringTag(3, "db.type");

    /**
     * DB_INSTANCE records database instance name.
     */
    public static final StringTag DB_INSTANCE = new StringTag(4, "db.instance");

    /**
     * DB_STATEMENT records the sql statement of the database access.
     */
    public static final StringTag DB_STATEMENT = new StringTag(5, "db.statement");

    /**
     * DB_BIND_VARIABLES records the bind variables of sql statement.
     */
    public static final StringTag DB_BIND_VARIABLES = new StringTag(6, "db.bind_vars");

    /**
     * MQ_QUEUE records the queue name of message-middleware.
     */
    public static final StringTag MQ_QUEUE = new StringTag(7, "mq.queue");

    /**
     * MQ_BROKER records the broker address of message-middleware.
     */
    public static final StringTag MQ_BROKER = new StringTag(8, "mq.broker");

    /**
     * MQ_TOPIC records the topic name of message-middleware.
     */
    public static final StringTag MQ_TOPIC = new StringTag(9, "mq.topic");

    /**
     * MQ_STATUS records the send/consume message status of message-middleware.
     */
    public static final StringTag MQ_STATUS = new StringTag(16, "mq_status");

    public static final StringTag MYBATIS_MAPPER = new StringTag(17, "mybatis.mapper");

    /**
     * The latency of transmission. When there are more than one downstream parent/segment-ref(s), multiple tags will be
     * recorded, such as a batch consumption in MQ.
     */
    public static final StringTag TRANSMISSION_LATENCY = new StringTag(15, "transmission.latency", false);

    /**
     * RESPONSE_CODE records the code string of the response. This is different from status code, which is
     * used to record http response code.
     */
    public static final StringTag RPC_RESPONSE_STATUS_CODE = new StringTag(18, "rpc.status_code", true);

    public static final class HTTP {
        public static final StringTag METHOD = new StringTag(10, "http.method");

        public static final StringTag PARAMS = new StringTag(11, "http.params", true);

        public static final StringTag BODY = new StringTag(13, "http.body");

        public static final StringTag HEADERS = new StringTag(14, "http.headers");
    }

    public static final StringTag LOGIC_ENDPOINT = new StringTag(12, "x-le");
    /**
     * CACHE_TYPE records cache type, such as jedis
     */
    public static final StringTag CACHE_TYPE = new StringTag(15, "cache.type");

    /**
     * CACHE_OP represent a command is used for "write" or "read"
     * It's better that adding this tag to span , so OAP would analysis write/read metric accurately
     * Reference org.apache.skywalking.apm.plugin.jedis.v4.AbstractConnectionInterceptor#parseOperation
     * BTW "op" means Operation
     */
    public static final StringTag CACHE_OP = new StringTag(16, "cache.op");

    /**
     * CACHE_TYPE records the cache command
     */
    public static final StringTag CACHE_CMD = new StringTag(17, "cache.cmd");

    /**
     * CACHE_TYPE records the cache key
     */
    public static final StringTag CACHE_KEY = new StringTag(18, "cache.key");

    /**
     * CACHE_INSTANCE records the cache instance
     */
    public static final StringTag CACHE_INSTANCE = new StringTag(20, "cache.instance");

    public static final String VAL_LOCAL_SPAN_AS_LOGIC_ENDPOINT = "{\"logic-span\":true}";

    public static final StringTag SQL_PARAMETERS = new StringTag(19, "db.sql.parameters");

    /**
     * LOCK_NAME records the lock name such as redisson lock name
     */
    public static final StringTag LOCK_NAME = new StringTag(21, "lock.name");

    /**
     * LEASE_TIME represents the maximum time to hold the lock after it's acquisition
     * in redisson plugin,it's unit is ms
     */
    public static final StringTag LEASE_TIME = new StringTag(22, "lease.time");

    /**
     * THREAD_ID records the thread id
     */
    public static final StringTag THREAD_ID = new StringTag(23, "thread.id");

    /**
     * THREAD_CARRIER represents the actual operating system thread that carries out the execution of the virtual thread.
     */
    public static final StringTag THREAD_CARRIER = new StringTag(24, "thread.carrier");

    /**
     * Creates a {@code StringTag} with the given key and cache it, if it's created before, simply return it without
     * creating a new one.
     *
     * @param key the {@code key} of the tag
     * @return the {@code StringTag}
     */
    public static AbstractTag<String> ofKey(final String key) {
        return TAG_PROTOTYPES.computeIfAbsent(key, StringTag::new);
    }
}
