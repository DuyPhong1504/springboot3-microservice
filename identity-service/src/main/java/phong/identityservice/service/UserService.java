package phong.identityservice.service;

import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import phong.identityservice.entity.UserEntity;
import phong.identityservice.model.UserCreateRequest;
import phong.identityservice.model.UserUpdateRequest;
import phong.identityservice.repository.UserRepository;

import java.util.List;

@Service
@Transactional
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public UserEntity createUser(UserCreateRequest request) {
        if(userRepository.findByUserName(request.getUserName()).isPresent()){
            throw new RuntimeException("Username already exit");
        }
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

    public UserEntity getUser(String id){
        return userRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found"));
    }

    public UserEntity updateUser(String id,UserUpdateRequest request){
        UserEntity userEntity = getUser(id);
        userEntity.setPassword(request.getPassword());
        userEntity.setEmail(request.getEmail());
        userEntity.setFullName(request.getFullName());
        userEntity.setDob(request.getDob());
        return userRepository.save(userEntity);
    }

    public boolean deleteUser(String id){
        UserEntity userEntity = getUser(id);
        userEntity.setDeleteFlg(true);
        return userRepository.save(userEntity).isDeleteFlg();
    }
}
