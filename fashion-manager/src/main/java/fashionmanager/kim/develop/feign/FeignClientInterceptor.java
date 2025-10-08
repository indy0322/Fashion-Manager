package fashionmanager.kim.develop.feign;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.apache.http.HttpHeaders;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

@Configuration
public class FeignClientInterceptor {
    @Bean
    public RequestInterceptor requestInterceptor() {

        /* 설명. RequestInterceptor의 하위 구현체 생성(Anonymous class 방식) */
        return new RequestInterceptor() {
            @Override
            public void apply(RequestTemplate requestTemplate) {
                ServletRequestAttributes requestAttributes =
                        (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();

                if(requestAttributes != null) {
                    String authorizationHeader = requestAttributes.getRequest()
                            .getHeader("Authorization");
                    if(authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
                        requestTemplate.header(HttpHeaders.AUTHORIZATION, authorizationHeader);
                    }
                }
            }
        };
    }
}
