package phong.notificationservice.controller;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.*;
import phong.notificationservice.dto.request.UserProfileCreateRequest;
import phong.notificationservice.dto.response.UserProfileResponse;
import phong.notificationservice.service.UserProfileService;

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
