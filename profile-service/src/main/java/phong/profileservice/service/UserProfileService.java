package phong.profileservice.service;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import phong.profileservice.dto.request.UserProfileCreateRequest;
import phong.profileservice.dto.response.UserProfileResponse;
import phong.profileservice.entity.UserProfileEntity;
import phong.profileservice.mapper.UserProfileMapper;
import phong.profileservice.repository.UserProfileRepository;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class UserProfileService {
    UserProfileRepository userProfileRepository;
    UserProfileMapper userProfileMapper;

    public UserProfileResponse createProfile(UserProfileCreateRequest request) {
        UserProfileEntity userProfileEntity = userProfileMapper.toUserProfileEntity(request);
        userProfileEntity = userProfileRepository.save(userProfileEntity);
        return userProfileMapper.toUserProfileResponse(userProfileEntity);
    }

    public UserProfileResponse getProfile(String id) {
        UserProfileEntity userProfileEntity = userProfileRepository.findById(id).orElseThrow(() -> new RuntimeException("Profile not found"));
        return userProfileMapper.toUserProfileResponse(userProfileEntity);
    }

    public boolean deleteProfile(String id) {
        UserProfileEntity userProfileEntity = userProfileRepository.findById(id).orElseThrow(() -> new RuntimeException("Profile not found"));
        userProfileRepository.delete(userProfileEntity);
        return true;
    }
}
