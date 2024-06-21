package phong.notificationservice.service;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;
import phong.notificationservice.model.request.CheckTokenRequest;
import phong.notificationservice.model.response.ApiResponse;
import phong.notificationservice.model.response.CheckTokenResponse;
import phong.notificationservice.repository.IdentityClient;
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
