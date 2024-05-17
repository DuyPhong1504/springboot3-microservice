package phong.identityservice.service;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import phong.identityservice.entity.UserEntity;
import phong.identityservice.enums.Role;
import phong.identityservice.exception.AppException;
import phong.identityservice.exception.ErrorCode;
import phong.identityservice.mapper.UserMapper;
import phong.identityservice.model.request.UserCreateRequest;
import phong.identityservice.model.request.UserUpdateRequest;
import phong.identityservice.repository.UserRepository;
import phong.identityservice.model.response.UserResponse;

import java.util.HashSet;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class UserService {

    UserRepository userRepository;

    UserMapper userMapper;

    PasswordEncoder passwordEncoder;


    public UserResponse createUser(UserCreateRequest request) {
        if (userRepository.findByUserName(request.getUserName()).isPresent()) {
            throw new AppException(ErrorCode.USER_EXISTED);
        }
        request.setPassword(passwordEncoder.encode(request.getPassword()));
        UserEntity userEntity = userMapper.toUserEntity(request);
        HashSet<String> roles = new HashSet<>();
        roles.add(Role.USER.name());
        userEntity.setRoles(roles);
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

    public UserResponse deleteUser(String id) {
        UserEntity userEntity = findUserById(id);
        userEntity.setDeleteFlg(true);
        return userMapper.toUserResponse(userRepository.save(userEntity));
    }

    UserEntity findUserById(String id) {
        return userRepository.findById(id).orElseThrow(() -> new AppException(ErrorCode.USER_NOT_EXISTED));
    }

    public UserResponse getInfo(){
        var context = SecurityContextHolder.getContext();
        String name = context.getAuthentication().getName();
        UserEntity userEntity = userRepository.findByUserName(name).orElseThrow(() -> new AppException(ErrorCode.USER_NOT_EXISTED));
        return userMapper.toUserResponse(userEntity);
    }
}
