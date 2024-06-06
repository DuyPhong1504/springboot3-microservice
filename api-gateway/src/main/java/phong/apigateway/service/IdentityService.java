package phong.apigateway.service;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;
import phong.apigateway.model.request.CheckTokenRequest;
import phong.apigateway.model.response.ApiResponse;
import phong.apigateway.model.response.CheckTokenResponse;
import phong.apigateway.repository.IdentityClient;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class IdentityService {
    IdentityClient identityClient;

    public Mono<ApiResponse<CheckTokenResponse>> checkToken(String token) {
        return identityClient.checkToken(CheckTokenRequest.builder()
                .token(token)
                .build());
    }
}
