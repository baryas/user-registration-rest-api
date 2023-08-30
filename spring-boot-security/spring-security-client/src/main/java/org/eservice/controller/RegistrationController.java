package org.eservice.controller;

import org.eservice.entity.User;
import org.eservice.event.RegistrationCompleteEvent;
import org.eservice.model.UserModel;
import org.eservice.service.UserService;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/api/v1")
public class RegistrationController {

    private UserService userService;

    private ApplicationEventPublisher eventPublisher;

    public RegistrationController(UserService userService, ApplicationEventPublisher eventPublisher) {
        this.userService = userService;
        this.eventPublisher = eventPublisher;

    }

    @PostMapping("/register")
    public String registerUser(@RequestBody UserModel userModel,
                               final HttpServletRequest httpServletRequest) {
        
        User user = userService.registerUser(userModel);

        eventPublisher.publishEvent
                (new RegistrationCompleteEvent(user, applicationUrl(httpServletRequest)));


        return "user has been registered";
    }

    private String applicationUrl(HttpServletRequest httpServletRequest) {
        return "http://"
                + httpServletRequest.getServerName()
                +":"
                +httpServletRequest.getServerPort()
                +httpServletRequest.getContextPath();


    }

}
