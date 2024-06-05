package phong.identityservice.mapper;

import org.mapstruct.Mapper;
import phong.identityservice.model.request.UserCreateRequest;
import phong.identityservice.model.request.UserProfileCreateRequest;

@Mapper
public interface UserProfileMapper {
    UserProfileCreateRequest toUserProfileCreateRequest(UserCreateRequest request);
}
