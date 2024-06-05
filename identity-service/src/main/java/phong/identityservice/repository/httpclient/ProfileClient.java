package phong.identityservice.repository.httpclient;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import phong.identityservice.model.request.UserProfileCreateRequest;
import phong.identityservice.model.response.UserProfileResponse;

@FeignClient(name = "profile-service", url = "${app.services.profile}")
public interface ProfileClient {
    @PostMapping(value = "internal/users", produces = MediaType.APPLICATION_JSON_VALUE)
    UserProfileResponse createProfile(@RequestBody UserProfileCreateRequest request);
}
