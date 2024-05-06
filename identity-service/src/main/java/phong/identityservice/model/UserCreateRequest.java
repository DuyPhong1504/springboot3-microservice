package phong.identityservice.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
public class UserCreateRequest {
    private String userName;
    private String password;
    private String email;
    private String fullName;
    private LocalDate dob;
}
