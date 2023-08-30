package org.eservice.event;

import lombok.extern.slf4j.Slf4j;
import org.eservice.entity.User;
import org.eservice.service.UserService;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@Slf4j
public class RegistrationCompleteEventListener implements
        ApplicationListener<RegistrationCompleteEvent> {

    private UserService userService;

    public RegistrationCompleteEventListener(UserService userService){
        this.userService = userService;
    }

    @Override
    public void onApplicationEvent
            (RegistrationCompleteEvent registrationCompleteEvent) {

        /* create a token verification Link on which user
           click, so it will redirect back to application*/

      User user =  registrationCompleteEvent.getUser();

        String token = UUID.randomUUID().toString();

        userService.saveVerificationForUser(token, user);


        // send email to user

        String url = registrationCompleteEvent.getApplicationUrl()
                + "/verifyRegistration?token="
                + token;

        // send verification email :

        log.info("Click the link here to verify your account: {}", url);



    }
}
