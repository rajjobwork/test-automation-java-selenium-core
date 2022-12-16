package com.version1.webdriver.configuration;

import java.io.IOException;

import com.version1.webdriver.configuration.utils.FileLoaderUtils;
import com.version1.webdriver.utils.JsonUtils;

public class SauceLabsConfigurationLoader {

    /**
     *
     * @param targetConfigurationFile
     * @return {@link SauceLabsConfig} loads SauceLabs configuration json from file or classpath
     */
    public SauceLabsConfig load(String targetConfigurationFile) {
        try {
            return JsonUtils.fromFile(
                    FileLoaderUtils.loadFromClasspathOrFileSystem(targetConfigurationFile), SauceLabsConfig.class);
        }
        catch (IOException e) {
            String message = "Unable to load configuration from " + targetConfigurationFile;
            if(e.getMessage().contains("SauceLabsCredentialsException")) {
                message = message + ": SauceLabsCredentialsException encountered.  Provide valid credentials.";
            }
            throw new RuntimeException(message);
        }
    }
}
