package phong.identityservice.response;


import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;

@Data
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserResponse {
    String id;
    String userName;
    String password;
    String email;
    String fullName;
    LocalDate dob;
    boolean deleteFlg;
}
