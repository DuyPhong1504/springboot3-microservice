package phong.identityservice.model.request;

import ch.qos.logback.core.spi.ErrorCodes;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;
import lombok.*;
import lombok.experimental.FieldDefaults;
import phong.identityservice.exception.ErrorCode;
import phong.identityservice.validator.DobConstraint;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserCreateRequest {
    @Size(min = 8, message = "USERNAME_INVALID")
    String userName;
    @Size(min = 8, message = "INVALID_PASSWORD")
    String password;
    @Email
    String email;
    String fullName;
    @DobConstraint(min = 10, message = "INVALID_DOB")
    LocalDate dob;
}
