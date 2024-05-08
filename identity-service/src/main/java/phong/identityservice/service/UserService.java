package phong.identityservice.service;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import phong.identityservice.entity.UserEntity;
import phong.identityservice.mapper.UserMapper;
import phong.identityservice.model.UserCreateRequest;
import phong.identityservice.model.UserUpdateRequest;
import phong.identityservice.repository.UserRepository;
import phong.identityservice.response.UserResponse;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class UserService {

    UserRepository userRepository;

    UserMapper userMapper;

    public UserResponse createUser(UserCreateRequest request) {
        if (userRepository.findByUserName(request.getUserName()).isPresent()) {
            throw new RuntimeException("Username already exit");
        }
        UserEntity userEntity = userMapper.toUserEntity(request);
        return userMapper.toUserResponse(userRepository.save(userEntity));
    }

    public List<UserResponse> getAllUser() {
        return userRepository.findAll().stream().map(userMapper::toUserResponse).toList();
    }

    public UserResponse getUser(String id) {
        return userMapper.toUserResponse(findUserById(id));
    }

    public UserResponse updateUser(String id, UserUpdateRequest request) {
        UserEntity userEntity = findUserById(id);
        userMapper.updateUser(userEntity, request);
        return userMapper.toUserResponse(userRepository.save(userEntity));
    }

    public boolean deleteUser(String id) {
        UserEntity userEntity = findUserById(id);
        userEntity.setDeleteFlg(true);
        return userRepository.save(userEntity).isDeleteFlg();
    }

    UserEntity findUserById(String id) {
        return userRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found"));
    }
}
