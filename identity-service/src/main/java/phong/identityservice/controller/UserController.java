package phong.identityservice.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import phong.identityservice.entity.UserEntity;
import phong.identityservice.model.UserCreateRequest;
import phong.identityservice.model.UserUpdateRequest;
import phong.identityservice.service.UserService;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/getAll")
    List<UserEntity> getAllUser() {
        return userService.getAllUser();
    }

    @PostMapping("/create")
    UserEntity createUser(@RequestBody @Valid UserCreateRequest request) {
        return userService.createUser(request);
    }

    @GetMapping("/{userId}")
    UserEntity getUser(@PathVariable("userId") String userId) {
        return userService.getUser(userId);
    }

    @PutMapping("/{userId}")
    UserEntity updateUser(@PathVariable("userId") String userId,@RequestBody UserUpdateRequest request){
        return userService.updateUser(userId,request);
    }

    @DeleteMapping("/{userId}")
    boolean deleteUser(@PathVariable("userId") String userId){
        return userService.deleteUser(userId);
    }
}
