package com.ivan.tpp.boot.config;

import org.springframework.boot.actuate.endpoint.RequestMappingEndpoint;
import org.springframework.context.SmartLifecycle;

public class TestSmartLifecycle implements SmartLifecycle {
	private boolean isRunning = false;
    @Override
    public void start() {
        System.out.println("test start-------------------------");
        isRunning = true;
    }

    @Override
    public void stop() {
        System.out.println("test stop-------------------------");
        isRunning = false;
        
    }

    @Override
    public boolean isRunning() {
        System.out.println("test isRunning-------------------------");
        return isRunning;
    }

    @Override
    public int getPhase() {
//    	EndpointMBeanExporter
//    	RequestMappingEndpoint
//    	ActuatorGetMapping
        System.out.println("test getPhase-------------------------");
        return 0;
    }

    @Override
    public boolean isAutoStartup() {
        System.out.println("test isAutoStartup-------------------------");
        return true;
    }

    @Override
    public void stop(Runnable callback) {
        System.out.println("test callback-------------------------");
        isRunning = false;
    }
}
