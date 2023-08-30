package org.eservice.event;

import lombok.Getter;
import lombok.Setter;
import org.eservice.entity.User;
import org.springframework.context.ApplicationEvent;

import java.time.Clock;

@Getter
@Setter
public class RegistrationCompleteEvent extends ApplicationEvent {

    private final User user;

    private final String applicationUrl;


    public RegistrationCompleteEvent(User user, String applicationUrl){
        super(user);
        this.user = user;
        this.applicationUrl = applicationUrl;

    }
}
