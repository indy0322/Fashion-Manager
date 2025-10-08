package msa.eurekagateway.filter;

import io.jsonwebtoken.Claims;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.List;

@Component
@Slf4j
public class JwtAuthenticationFilter extends AbstractGatewayFilterFactory<JwtAuthenticationFilter.Config> {


    private final JwtUtil jwtUtil;

    public JwtAuthenticationFilter(JwtUtil jwtUtil) {
        super(Config.class);
        this.jwtUtil = jwtUtil;
    }

    public static class Config {
    }

    @Override
    public GatewayFilter apply(Config config) {
        return (exchange, chain) -> {
            String authHeader = exchange.getRequest().getHeaders().getFirst(HttpHeaders.AUTHORIZATION);
            System.out.println(">>> Gateway JwtAuthenticationFilter triggered for " + exchange.getRequest().getURI());
            if (authHeader == null || !authHeader.startsWith("Bearer ")) {
                return onError(exchange, "No or invalid authorization header", HttpStatus.UNAUTHORIZED);
            }

            String token = authHeader.substring(7);

            if (!jwtUtil.validateToken(token)) {
                return onError(exchange, "Invalid or Expired JWT Token", HttpStatus.UNAUTHORIZED);
            }

            // JWT에서 Claims 추출
            Claims claims = jwtUtil.getClaims(token);
            String memberId = claims.getSubject();
            String role = claims.get("role", String.class);

            // 로그 출력
            log.info("Gateway JWT Authenticated: memberId={}, role={}", memberId, role);
            System.out.println("Gateway JWT Authenticated: memberId=" + memberId + ", role=" + role);

            // 필요하면 authorities 리스트 생성
            List<String> authorities = List.of("ROLE_" + role);
            log.info("Authorities in Gateway Filter: {}", authorities);
            System.out.println("Authorities in Gateway Filter: " + authorities);

            return chain.filter(exchange);
        };
    }
    private Mono<Void> onError(ServerWebExchange exchange, String missingAuthorizationHeader, HttpStatus httpStatus) {

        exchange.getResponse().setStatusCode(httpStatus);
        return exchange.getResponse().setComplete();
    }
}


