package phong.identityservice.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import phong.identityservice.entity.UserEntity;
import phong.identityservice.model.UserCreateRequest;
import phong.identityservice.model.UserUpdateRequest;
import phong.identityservice.response.UserResponse;

@Mapper
public interface UserMapper {
    UserEntity toUserEntity(UserCreateRequest request);

    UserResponse toUserResponse(UserEntity entity);

    void  updateUser(@MappingTarget UserEntity userEntity, UserUpdateRequest request);

}
