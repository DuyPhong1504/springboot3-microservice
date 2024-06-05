package phong.identityservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableCaching
@EnableFeignClients
public class IdentityService {
    public static void main(String[] args) {
        SpringApplication.run(IdentityService.class, args);
    }
}