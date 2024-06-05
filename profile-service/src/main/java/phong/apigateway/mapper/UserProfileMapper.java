package phong.apigateway.mapper;

import org.mapstruct.Mapper;
import phong.apigateway.dto.request.UserProfileCreateRequest;
import phong.apigateway.dto.response.UserProfileResponse;
import phong.apigateway.entity.UserProfileEntity;

@Mapper
public interface UserProfileMapper {
    UserProfileEntity toUserProfileEntity(UserProfileCreateRequest request);

    UserProfileResponse toUserProfileResponse(UserProfileEntity entity);

}
