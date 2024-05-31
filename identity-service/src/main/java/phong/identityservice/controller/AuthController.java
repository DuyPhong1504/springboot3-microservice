package phong.identityservice.controller;

import com.nimbusds.jose.JOSEException;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import phong.identityservice.model.request.AuthenticationRequest;
import phong.identityservice.model.request.CheckTokenRequest;
import phong.identityservice.model.request.RefreshRequest;
import phong.identityservice.model.response.ApiResponse;
import phong.identityservice.model.response.AuthenticationResponse;
import phong.identityservice.model.response.CheckTokenResponse;
import phong.identityservice.service.AuthenticationService;

import java.text.ParseException;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AuthController {
    AuthenticationService authenticationService;

    @PostMapping("/getToken")
    ApiResponse<AuthenticationResponse> authenticate(@RequestBody AuthenticationRequest request) {
        AuthenticationResponse response = authenticationService.authenticate(request);
        return ApiResponse.<AuthenticationResponse>builder()
                .result(response)
                .build();
    }

    @PostMapping("/checkToken")
    ApiResponse<CheckTokenResponse> checkToken(@RequestBody CheckTokenRequest request) throws ParseException, JOSEException {
        CheckTokenResponse response = authenticationService.checkToken(request);
        return ApiResponse.<CheckTokenResponse>builder()
                .result(response)
                .build();
    }

//    @PostMapping("/test")
//    ApiResponse<AuthenticationResponse> refreshToken(@RequestBody RefreshRequest request) throws ParseException, JOSEException {
//        var result = authenticationService.refreshToken(request);
//        return ApiResponse.<AuthenticationResponse>builder().result(result).build();
//    }

    @PostMapping("/refreshToken")
    ApiResponse<CheckTokenResponse> refreshToken(@RequestBody CheckTokenRequest request) throws ParseException, JOSEException {
        var result = authenticationService.refreshToken(request);
        return ApiResponse.<CheckTokenResponse>builder().result(result).build();
    }
}
