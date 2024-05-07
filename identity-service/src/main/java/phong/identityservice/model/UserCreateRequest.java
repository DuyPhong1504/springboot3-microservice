package phong.identityservice.model;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
public class UserCreateRequest {
    @Size(min = 8,message = "username must be at lest 8 character")
    private String userName;
    @Size(min = 8,message = "password must be at lest 8 character")
    private String password;
    @Email
    private String email;
    private String fullName;
    private LocalDate dob;
}
