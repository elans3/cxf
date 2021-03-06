/**
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements. See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership. The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */
package org.apache.cxf.rt.security.crypto;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.apache.cxf.common.util.StringUtils;

/**
 * The utility Message Digest generator which can be used for generating
 * random values
 */
public final class MessageDigestUtils {
    
    public static final String ALGO_SHA_1 = "SHA-1";
    public static final String ALGO_SHA_256 = "SHA-256";
    public static final String ALGO_MD5 = "MD5";
    
    private MessageDigestUtils() {
        
    }
        
    public static String generate(byte[] input) {
        return generate(input, ALGO_SHA_256);
    }   
    
    public static String generate(byte[] input, String algo) {    
        try {
            byte[] messageDigest = createDigest(input, algo);
            return StringUtils.toHexString(messageDigest);
        } catch (NoSuchAlgorithmException e) {
            throw new SecurityException(e);
        }
    }

    public static byte[] createDigest(String input, String algo) {
        try {
            return createDigest(input.getBytes("UTF-8"), algo);
        } catch (UnsupportedEncodingException e) {
            throw new SecurityException(e);
        } catch (NoSuchAlgorithmException e) {
            throw new SecurityException(e);
        }   
    }
    
    public static byte[] createDigest(byte[] input, String algo) throws NoSuchAlgorithmException { 
        MessageDigest md = MessageDigest.getInstance(algo);
        return md.digest(input);
    }
    
}
