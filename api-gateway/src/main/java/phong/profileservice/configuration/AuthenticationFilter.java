package phong.profileservice.configuration;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.experimental.NonFinal;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.web.server.ServerWebExchange;
import phong.profileservice.model.response.ApiResponse;
import phong.profileservice.service.IdentityService;
import reactor.core.publisher.Mono;
import org.springframework.http.server.reactive.ServerHttpResponse;

import java.util.Arrays;
import java.util.List;


@Component
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PACKAGE, makeFinal = true)
public class AuthenticationFilter implements GlobalFilter, Ordered {
    IdentityService identityService;
    ObjectMapper objectMapper;

    @Value("${app.api-prefix}")
    @NonFinal
    private String API_PREFIX;

    @NonFinal
    private String[] PUBLIC_ENDPOINTS = {"/identity/auth/.*"};


    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        if (checkPublicEndpoint(exchange)) {
            return chain.filter(exchange);
        }

        // Get token from header
        List<String> authHeader = exchange.getRequest().getHeaders().get(HttpHeaders.AUTHORIZATION);
        if (CollectionUtils.isEmpty(authHeader)) {
            return unAuthentication(exchange.getResponse());
        }
        // verify token
        // delegate identity service
        String token = authHeader.getFirst().replace("Bearer ", "");
        return identityService.checkToken(token).flatMap(introspectResponse -> {
            if (introspectResponse.getResult().isValid())
                return chain.filter(exchange);
            else
                return unAuthentication(exchange.getResponse());
        }).onErrorResume(throwable -> unAuthentication(exchange.getResponse()));
    }

    private boolean checkPublicEndpoint(ServerWebExchange exchange) {
        var request = exchange.getRequest();
        return Arrays.stream(PUBLIC_ENDPOINTS).anyMatch(endPoint -> request.getURI().getPath().matches(API_PREFIX + endPoint));
    }

    @Override
    public int getOrder() {
        return -1;
    }

    Mono<Void> unAuthentication(ServerHttpResponse response) {
        String body = null;
        response.setStatusCode(HttpStatus.UNAUTHORIZED);
        ApiResponse<?> apiResponse = ApiResponse.builder()
                .code(1401)
                .message("Unauthenticated")
                .build();
        try {
            body = objectMapper.writeValueAsString(apiResponse);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        response.setStatusCode(HttpStatus.UNAUTHORIZED);
        response.getHeaders().add(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);

        return response.writeWith(
                Mono.just(response.bufferFactory().wrap(body.getBytes())));
    }
}
