package com.tryfinch.apichallenge.resolver.startstopengine;

import lombok.Data;

@Data
public class StartStopEngineRequest {
    private String vehicleId;
    private StartStopEngineCommand command;
}
