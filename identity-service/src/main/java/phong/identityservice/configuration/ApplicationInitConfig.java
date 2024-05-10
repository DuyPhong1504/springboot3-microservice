package phong.identityservice.configuration;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;
import phong.identityservice.entity.UserEntity;
import phong.identityservice.enums.Role;
import phong.identityservice.repository.UserRepository;

import java.util.HashSet;

@Configuration
@FieldDefaults(level = AccessLevel.PRIVATE,makeFinal = true)
@RequiredArgsConstructor
@Slf4j
public class ApplicationInitConfig {

    PasswordEncoder passwordEncoder;
    @Bean
    ApplicationRunner applicationRunner(UserRepository userRepository){
        return args -> {
            if(userRepository.findByUserName("admin1234").isEmpty()){
                String hashPassword = passwordEncoder.encode("admin1234");
                var roles = new HashSet<String>();
                roles.add(Role.ADMIN.name());
                UserEntity user = UserEntity.builder().
                        userName("admin1234")
                        .password(hashPassword)
                        .email("admin@gmail.com")
                        .roles(roles)
                        .build();
                userRepository.save(user);
                log.warn("admin user created by default password");
            }
        };
    }
}

