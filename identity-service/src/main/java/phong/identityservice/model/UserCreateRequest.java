package phong.identityservice.model;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserCreateRequest {
    @Size(min = 8, message = "username must be at lest 8 character")
    String userName;
    @Size(min = 8, message = "password must be at lest 8 character")
    String password;
    @Email
    String email;
    String fullName;
    LocalDate dob;
}
