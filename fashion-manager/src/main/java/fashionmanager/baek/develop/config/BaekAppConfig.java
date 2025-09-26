package fashionmanager.baek.develop.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
<<<<<<<< HEAD:fashion-manager/src/main/java/fashionmanager/baek/develop/config/AppConfig1.java
public class AppConfig1 {
========
public class BaekAppConfig {
    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }
>>>>>>>> features:fashion-manager/src/main/java/fashionmanager/baek/develop/config/BaekAppConfig.java
}
