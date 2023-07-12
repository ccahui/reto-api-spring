package com.reto.cliente.service;


import com.reto.cliente.config.YamlPropertySourceFactory;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Component
@PropertySource(
        value = {"${spring.system.active}/${spring.application.name}/azure-upload-${spring.profiles.active}.yml"},
        factory = YamlPropertySourceFactory.class
)
@ConfigurationProperties("storage")
@Getter
@Setter
public class AzureUploadConfig {
    private String connectionSasContainerString;
}
