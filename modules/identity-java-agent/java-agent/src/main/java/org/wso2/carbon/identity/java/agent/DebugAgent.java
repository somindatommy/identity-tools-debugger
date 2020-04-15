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

package org.wso2.carbon.identity.java.agent;

import org.wso2.carbon.identity.java.agent.config.InterceptorConfig;
import org.wso2.carbon.identity.java.agent.config.InterceptorConfigReader;
import org.wso2.carbon.identity.java.agent.internal.InterceptingClassTransformer;

import java.lang.instrument.Instrumentation;
import java.util.List;
import java.util.logging.Logger;

/**
 * Java Agent to intercept the given Java Classes and add the relevant methods.
 */
public class DebugAgent {

    private static final Logger log = Logger.getLogger(DebugAgent.class.getName());

    /**
     * As soon as the JVM initializes, This  method will be called.
     * Configs for intercepting will be read and added to Transformer so that Transformer will intercept when the
     * corresponding Java Class and Method is loaded.
     *
     * @param agentArgs       the list of agent arguments
     * @param instrumentation the instrumentation object
     * @throws InstantiationException while  an instantiation of object cause an error.
     */
    public static void premain(String agentArgs, Instrumentation instrumentation) throws InstantiationException {

        log.info("Starting WSO2 Debugger Java Agent......");
        InterceptorConfigReader interceptorConfigReader = new InterceptorConfigReader();
        List<InterceptorConfig> configList = interceptorConfigReader.readConfig();

        InterceptingClassTransformer interceptingClassTransformer = new InterceptingClassTransformer();
        interceptingClassTransformer.init();

        for (InterceptorConfig interceptorConfig : configList) {
            interceptingClassTransformer.addConfig(interceptorConfig);
        }

        instrumentation.addTransformer(interceptingClassTransformer);
    }

}
