package phong.identityservice.controller;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import phong.identityservice.model.request.UserCreateRequest;
import phong.identityservice.model.request.UserUpdateRequest;
import phong.identityservice.model.response.ApiResponse;
import phong.identityservice.model.response.UserResponse;
import phong.identityservice.service.UserService;

import java.util.List;

@RestController
@RequestMapping("/users")
@Slf4j
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/getAll")
    @PreAuthorize("hasAuthority('ADMIN')")
    ApiResponse<List<UserResponse>> getAllUser() {
        log.info("in method");
        var authentication = SecurityContextHolder.getContext().getAuthentication();
        log.info(authentication.getName());
        authentication.getAuthorities().forEach(role -> log.info("role : {}", role));
        ApiResponse<List<UserResponse>> apiResponse = new ApiResponse<List<UserResponse>>();
        apiResponse.setResult(userService.getAllUser());
        return apiResponse;
    }

    @PostMapping("/create")
    ApiResponse<UserResponse> createUser(@RequestBody @Valid UserCreateRequest request) {
        ApiResponse<UserResponse> apiResponse = new ApiResponse<UserResponse>();
        apiResponse.setResult(userService.createUser(request));
        return apiResponse;
    }

    @GetMapping("/{userId}")
    @PostAuthorize("returnObject.result.userName == authentication.name")
    ApiResponse<UserResponse> getUser(@PathVariable("userId") String userId) {
        log.info("In method get userID");
        ApiResponse<UserResponse> apiResponse = new ApiResponse<UserResponse>();
        apiResponse.setResult(userService.getUser(userId));
        return apiResponse;
    }

    @PutMapping("/{userId}")
    ApiResponse<UserResponse> updateUser(@PathVariable("userId") String userId, @RequestBody UserUpdateRequest request) {
        ApiResponse<UserResponse> apiResponse = new ApiResponse<UserResponse>();
        apiResponse.setResult(userService.updateUser(userId, request));
        return apiResponse;
    }

    @DeleteMapping("/{userId}")
    ApiResponse<UserResponse> deleteUser(@PathVariable("userId") String userId) {
        ApiResponse<UserResponse> apiResponse = new ApiResponse<UserResponse>();
        apiResponse.setResult(userService.deleteUser(userId));
        return apiResponse;
    }

    @GetMapping("/getInfo")
    ApiResponse<UserResponse> getInfo() {
        log.info("in method getInfo");
        var authentication = SecurityContextHolder.getContext().getAuthentication();
        log.info(authentication.getName());
        authentication.getAuthorities().forEach(role -> log.info("role : {}", role));
        ApiResponse<UserResponse> apiResponse = new ApiResponse<UserResponse>();
        apiResponse.setResult(userService.getInfo());
        return apiResponse;
    }
}
