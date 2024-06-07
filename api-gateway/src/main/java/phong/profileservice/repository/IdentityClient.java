package phong.profileservice.repository;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.service.annotation.PostExchange;
import phong.profileservice.model.request.CheckTokenRequest;
import phong.profileservice.model.response.ApiResponse;
import phong.profileservice.model.response.CheckTokenResponse;
import reactor.core.publisher.Mono;

public interface IdentityClient {
    @PostExchange(url = "/auth/checkToken", contentType = MediaType.APPLICATION_JSON_VALUE)
    Mono<ApiResponse<CheckTokenResponse>> checkToken(@RequestBody CheckTokenRequest request);
}
