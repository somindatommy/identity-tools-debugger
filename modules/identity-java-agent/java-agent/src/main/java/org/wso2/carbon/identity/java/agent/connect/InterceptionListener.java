/*
 * Copyright (c) 2019, WSO2 Inc. (http://www.wso2.org) All Rights Reserved.
 *
 * WSO2 Inc. licenses this file to you under the Apache License,
 * Version 2.0 (the "License"); you may not use this file except
 * in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */

package org.wso2.carbon.identity.java.agent.connect;

import org.wso2.carbon.identity.java.agent.host.InterceptionEventType;
import org.wso2.carbon.identity.java.agent.host.MethodContext;

/**
 * Listener for instrumentation event.
 * Should be implemented by any class want to listen to any execution event on class or method.
 */
public interface InterceptionListener {

    /**
     * Handles the event.
     * Implementor may block the thread or handle it asynchronously.
     *
     * @param type          the type of the event.
     * @param methodContext instrumentation context details for a Method.
     */
    void handleEvent(InterceptionEventType type, MethodContext methodContext);
}
