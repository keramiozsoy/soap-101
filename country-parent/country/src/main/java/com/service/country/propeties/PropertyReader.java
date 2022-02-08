package com.service.country.propeties;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Setter
@Getter
@ConfigurationProperties(prefix = "file")
@Configuration
public class PropertyReader {
    private String filename;
    private String generatedFolder;
    private String uploadFtpVolumeFolder;
    private String uploadFtpFolder;
    private String username;
    private String password;
    private String hostname;
    private int port;
}
