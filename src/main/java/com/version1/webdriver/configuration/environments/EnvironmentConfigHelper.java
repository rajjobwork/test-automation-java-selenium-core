package com.version1.webdriver.configuration.environments;

import com.fasterxml.jackson.core.type.TypeReference;
import com.version1.webdriver.utils.JsonUtils;

import lombok.SneakyThrows;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Map;

public class EnvironmentConfigHelper {

    private static Map<String, String> environmentConfig;
    private static EnvironmentConfigHelper environmentConfigHelper;

    private EnvironmentConfigHelper() throws Exception {
        String filename = System.getProperty("ENVIRONMENT_CONFIG", "environment.json");
        if(!filename.isEmpty()) {
            String environmentConfigurationJsonAsString = FileUtils.readFileToString(new File(ClassLoader.getSystemResource(filename).toURI()), Charset.forName("UTF-8"));
            this.environmentConfig =
                    JsonUtils.fromString(environmentConfigurationJsonAsString, new TypeReference<HashMap<String, String>>() {
            });
        } else {
            throw new Exception("Environment Configuration not set in environment variable ENVIRONMENT_CONFIG or in default environments.json");
        }
    }

    /**
     *
     * @return TestConfigManager returns the instance of the singleton to access the member methods
     * @throws Exception 
     */
    @SneakyThrows
    public static String get(String key) throws Exception {
        if(null == environmentConfig) {
            environmentConfigHelper = new EnvironmentConfigHelper();
        }
        return environmentConfig.get(key);
    }
}
