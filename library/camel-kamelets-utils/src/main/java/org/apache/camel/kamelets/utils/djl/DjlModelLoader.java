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
package org.apache.camel.kamelets.utils.djl;

import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Path;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ai.djl.Model;
import ai.djl.util.ZipUtils;

/**
 * A factory class that loads a DJL model from a URL.
 */
public class DjlModelLoader {

    private static final Logger LOG = LoggerFactory.getLogger(DjlModelLoader.class);

    public static Model loadModel(String modelName, String modelUrl) throws Exception {
        LOG.info("Loading model from {}", modelUrl);
        Model model = Model.newInstance(modelName);
        Path modelDir = Files.createTempDirectory(modelName);
        ZipUtils.unzip(new URI(modelUrl).toURL().openStream(), modelDir);
        model.load(modelDir);
        LOG.info("Model loaded: {}", model);
        return model;
    }
}
