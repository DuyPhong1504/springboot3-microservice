package phong.notificationservice.controller;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import phong.notificationservice.dto.response.UserProfileResponse;
import phong.notificationservice.service.UserProfileService;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class UserProfileController {
    UserProfileService userProfileService;

    @GetMapping("/{profileId}")
    @PreAuthorize("hasAuthority('ADMIN')")
    UserProfileResponse getProfile(@PathVariable String profileId) {
        var authentication = SecurityContextHolder.getContext().getAuthentication();
        log.info(authentication.getName());
        authentication.getAuthorities().forEach(role -> log.info("role : {}", role));
        return userProfileService.getProfile(profileId);
    }

    @DeleteMapping("/{profileId}")
    boolean deleteProfile(@PathVariable String profileId) {
        return userProfileService.deleteProfile(profileId);
    }
}
