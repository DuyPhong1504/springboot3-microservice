package phong.identityservice.service;

import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import phong.identityservice.entity.UserEntity;
import phong.identityservice.model.UserCreateRequest;
import phong.identityservice.repository.UserRepository;

import java.util.List;

@Service
@Transactional
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public UserEntity createUser(UserCreateRequest request) {
        UserEntity userEntity = UserEntity.builder()
                .userName(request.getUserName())
                .password(request.getPassword())
                .email(request.getEmail())
                .fullName(request.getFullName())
                .dob(request.getDob())
                .build();
        return  userRepository.save(userEntity);
    }

    public List<UserEntity> getAllUser(){
        return userRepository.findAll();
    }
}
