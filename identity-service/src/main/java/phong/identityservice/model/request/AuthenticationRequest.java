package phong.identityservice.model.request;

import lombok.Data;

@Data
public class AuthenticationRequest {
    String userName;
    String password;
}
