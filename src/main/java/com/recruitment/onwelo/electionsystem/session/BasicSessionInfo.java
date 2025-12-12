package com.recruitment.onwelo.electionsystem.session;

import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

import java.util.UUID;

@Component
@SessionScope
public class BasicSessionInfo {
    private final UUID sessionIdentifier = UUID.randomUUID();

    public String getSessionInfo() {
        return String.valueOf(this.sessionIdentifier);
    }
}
