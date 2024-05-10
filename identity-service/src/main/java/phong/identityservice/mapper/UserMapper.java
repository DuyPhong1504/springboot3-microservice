package phong.identityservice.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import phong.identityservice.entity.UserEntity;
import phong.identityservice.model.request.UserCreateRequest;
import phong.identityservice.model.request.UserUpdateRequest;
import phong.identityservice.model.response.UserResponse;

@Mapper
public interface UserMapper {
    UserEntity toUserEntity(UserCreateRequest request);

    @Mapping(target = "password", ignore = true)
    UserResponse toUserResponse(UserEntity entity);

    void  updateUser(@MappingTarget UserEntity userEntity, UserUpdateRequest request);

}
