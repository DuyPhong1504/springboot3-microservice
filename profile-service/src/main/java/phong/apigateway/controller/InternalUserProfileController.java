package phong.apigateway.controller;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.*;
import phong.apigateway.dto.request.UserProfileCreateRequest;
import phong.apigateway.dto.response.UserProfileResponse;
import phong.apigateway.service.UserProfileService;

@RestController
@RequestMapping("internal/users")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class InternalUserProfileController {
    UserProfileService userProfileService;

    @PostMapping()
    UserProfileResponse createProfileResponse(@RequestBody UserProfileCreateRequest request) {
        return userProfileService.createProfile(request);
    }
}
