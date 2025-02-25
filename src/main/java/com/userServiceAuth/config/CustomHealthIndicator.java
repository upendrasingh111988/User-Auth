package com.userServiceAuth.config;

import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.stereotype.Component;

@Component
public class CustomHealthIndicator implements HealthIndicator {

    @Override
    public Health health() {

        boolean appHealthyCheck= checkDatabaseConnection();
        if (appHealthyCheck){
            return Health.up().withDetail("message","Database is up").build();
        }else {
            return Health.down().withDetail("message","Database is down").build();
        }

    }
    private boolean checkDatabaseConnection(){
        return true;
    }
}
