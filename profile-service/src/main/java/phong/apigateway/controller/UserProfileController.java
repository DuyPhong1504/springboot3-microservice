package phong.apigateway.controller;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.*;
import phong.apigateway.dto.request.UserProfileCreateRequest;
import phong.apigateway.dto.response.UserProfileResponse;
import phong.apigateway.service.UserProfileService;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class UserProfileController {
    UserProfileService userProfileService;

    @GetMapping("/{profileId}")
    UserProfileResponse getProfile(@PathVariable String profileId) {
        return userProfileService.getProfile(profileId);
    }

    @DeleteMapping("/{profileId}")
    boolean deleteProfile(@PathVariable String profileId) {
        return userProfileService.deleteProfile(profileId);
    }
}
