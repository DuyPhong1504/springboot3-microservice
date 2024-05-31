package phong.identityservice.model.response;


import lombok.*;
import lombok.experimental.FieldDefaults;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Set;

@Data
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserResponse implements Serializable {
    private static final long serialVersionUID = 1L;
    String id;
    String userName;
    String password;
    String email;
    String fullName;
    LocalDate dob;
    boolean deleteFlg;
    Set<String> roles;
}
