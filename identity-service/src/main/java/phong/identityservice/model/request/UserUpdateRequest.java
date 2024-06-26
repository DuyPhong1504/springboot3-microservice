package phong.identityservice.model.request;

import lombok.Data;

import java.time.LocalDate;

@Data
public class UserUpdateRequest {
    private String password;
    private String email;
    private String fullName;
    private LocalDate dob;
}
