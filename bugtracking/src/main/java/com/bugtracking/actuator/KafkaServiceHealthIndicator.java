package com.bugtracking.actuator;

import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.stereotype.Component;

@Component
public class KafkaServiceHealthIndicator implements HealthIndicator {
    public boolean isServiceRunning;
    @Override
    public Health health() {
        if(checkServiceRunning()){
            return Health.up().withDetail("Kafka", "Running").build();
        }else{
            return Health.down().withDetail("Kafka", "Not Running").build();

        }
    }


    public Boolean checkServiceRunning(){
        return true;
    }

}
