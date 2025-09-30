package fashionmanager.kim.develop.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.models.OpenAPI;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


/* 설명. http://localhost:8081/swagger-ui/index.html */
@OpenAPIDefinition(
        info = @Info(
                title = "Spring Boot REST API 명세서",
                description = "Spring Boot와 REST API를 학습하기 위한 API 문서",
                version = "v1.0.0"
        )
)
@Configuration
public class SwaggerConfiguration {

}

