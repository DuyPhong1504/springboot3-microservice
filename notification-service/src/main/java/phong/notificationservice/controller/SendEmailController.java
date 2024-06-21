package phong.notificationservice.controller;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import phong.notificationservice.dto.request.SendEmailRequest;
import phong.notificationservice.dto.response.ApiResponse;
import phong.notificationservice.service.SendEmailservice;

@RestController
@RequestMapping("/sendMail")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class SendEmailController {
    SendEmailservice sendEmailservice;

    @PostMapping()
    ApiResponse<SendEmailRequest> authenticate(@RequestBody SendEmailRequest request) {
        sendEmailservice.sendEmailSmtpGmail(request);
        return ApiResponse.<SendEmailRequest>builder().result(request).build();
    }
}
