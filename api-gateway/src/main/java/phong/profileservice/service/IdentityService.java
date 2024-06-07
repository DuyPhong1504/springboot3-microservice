package phong.profileservice.service;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;
import phong.profileservice.model.request.CheckTokenRequest;
import phong.profileservice.model.response.ApiResponse;
import phong.profileservice.model.response.CheckTokenResponse;
import phong.profileservice.repository.IdentityClient;
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
