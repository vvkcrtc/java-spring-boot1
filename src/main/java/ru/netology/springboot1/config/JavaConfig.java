package ru.netology.springboot1.config;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.netology.springboot1.SystemProfile.DevProfile;
import ru.netology.springboot1.SystemProfile.ProductionProfile;
import ru.netology.springboot1.SystemProfile.SystemProfile;
@Configuration
public class JavaConfig {
    @Bean
    //@ConditionalOnProperty(prefix = "netology.profile", name = "dev", havingValue = "true")
    @ConditionalOnProperty(value = "netology.profile.dev", havingValue = "true", matchIfMissing = true)
    public SystemProfile devProfile() {
        return new DevProfile();
    }

    @Bean
    @ConditionalOnProperty(value = "netology.profile.dev",  havingValue = "false")
    public SystemProfile prodProfile() {
        return new ProductionProfile();
    }
}
