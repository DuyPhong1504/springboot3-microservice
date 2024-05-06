package phong.identityservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import phong.identityservice.entity.UserEntity;
import phong.identityservice.model.UserCreateRequest;
import phong.identityservice.service.UserService;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/getAll")
    List<UserEntity> getAllUser(){
        return userService.getAllUser();
    }

    @PostMapping("/create")
    UserEntity createUser(@RequestBody UserCreateRequest request){
        return userService.createUser(request);
    }
}
