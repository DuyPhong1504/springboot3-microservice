package phong.identityservice.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import phong.identityservice.model.request.UserCreateRequest;
import phong.identityservice.model.request.UserUpdateRequest;
import phong.identityservice.model.response.ApiResponse;
import phong.identityservice.model.response.UserResponse;
import phong.identityservice.service.UserService;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/getAll")
    ApiResponse getAllUser() {
        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setResult(userService.getAllUser());
        return apiResponse;
    }

    @PostMapping("/create")
    UserResponse createUser(@RequestBody @Valid UserCreateRequest request) {
        return userService.createUser(request);
    }

    @GetMapping("/{userId}")
    UserResponse getUser(@PathVariable("userId") String userId) {
        return userService.getUser(userId);
    }

    @PutMapping("/{userId}")
    UserResponse updateUser(@PathVariable("userId") String userId,@RequestBody UserUpdateRequest request){
        return userService.updateUser(userId,request);
    }

    @DeleteMapping("/{userId}")
    boolean deleteUser(@PathVariable("userId") String userId){
        return userService.deleteUser(userId);
    }
}
