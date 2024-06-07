package phong.profileservice.mapper;

import org.mapstruct.Mapper;
import phong.profileservice.dto.request.UserProfileCreateRequest;
import phong.profileservice.dto.response.UserProfileResponse;
import phong.profileservice.entity.UserProfileEntity;

@Mapper
public interface UserProfileMapper {
    UserProfileEntity toUserProfileEntity(UserProfileCreateRequest request);

    UserProfileResponse toUserProfileResponse(UserProfileEntity entity);

}
