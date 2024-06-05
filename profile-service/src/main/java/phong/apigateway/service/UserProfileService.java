package phong.apigateway.service;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import phong.apigateway.dto.request.UserProfileCreateRequest;
import phong.apigateway.dto.response.UserProfileResponse;
import phong.apigateway.entity.UserProfileEntity;
import phong.apigateway.mapper.UserProfileMapper;
import phong.apigateway.repository.UserProfileRepository;

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
