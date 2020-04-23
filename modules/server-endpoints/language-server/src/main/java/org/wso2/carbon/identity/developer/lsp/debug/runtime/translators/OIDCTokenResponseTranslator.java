/*
 * Copyright (c) 2020, WSO2 Inc. (http://www.wso2.org) All Rights Reserved.
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

package org.wso2.carbon.identity.developer.lsp.debug.runtime.translators;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.wso2.carbon.identity.base.IdentityException;
import org.wso2.carbon.identity.developer.lsp.debug.DAPConstants;
import org.wso2.carbon.identity.sso.saml.util.SAMLSSOUtil;

import java.lang.reflect.Field;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;

/**
 * Translator to translate the  SAML Request arguments.
 */
public class OIDCTokenResponseTranslator implements VariableTranslator {

    private static Log log = LogFactory.getLog(OIDCTokenResponseTranslator.class);

    private OIDCTokenResponseTranslator() {

    }

    private static class OIDCAuthzRequestTranslatorHolder {

        private static final OIDCTokenResponseTranslator INSTANCE = new OIDCTokenResponseTranslator();
    }

    /**
     * This static method allow to get the instance of the SAMLRequestTranslator.
     *
     * @return The SAMLRequestTranslatorHolder instance.
     */
    public static OIDCTokenResponseTranslator getInstance() {

        return OIDCAuthzRequestTranslatorHolder.INSTANCE;
    }

    @Override
    public Object translate(Object object, int variablesReference) {

        if (object != null) {
            HashMap<String, Object> requestDetails = new HashMap<>();
            for (Field field : object.getClass().getDeclaredFields()) {
                field.setAccessible(true);
                Object value = null;
                try {
                    value = field.get(object);
                } catch (IllegalAccessException e) {
                    log.error("Could not found the class", e);
                }
                if (value != null) {
                    requestDetails.put(field.getName(), value);
                }
            }
            return requestDetails;
        }
        return object;
    }
}
