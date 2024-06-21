package phong.notificationservice.mapper;

import org.mapstruct.Mapper;
import phong.notificationservice.dto.request.UserProfileCreateRequest;
import phong.notificationservice.dto.response.UserProfileResponse;
import phong.notificationservice.entity.UserProfileEntity;

@Mapper
public interface UserProfileMapper {
    UserProfileEntity toUserProfileEntity(UserProfileCreateRequest request);

    UserProfileResponse toUserProfileResponse(UserProfileEntity entity);

}
