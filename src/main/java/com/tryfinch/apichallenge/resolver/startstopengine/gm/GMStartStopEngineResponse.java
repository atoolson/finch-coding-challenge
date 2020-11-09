package com.tryfinch.apichallenge.resolver.startstopengine.gm;

import com.tryfinch.apichallenge.resolver.startstopengine.StartStopEngineResponse;
import lombok.Data;

@Data
public class GMStartStopEngineResponse {
    private String service;
    private String status;
    private GMStartStopEngineActionResult actionResult;

    public StartStopEngineResponse toStartStopEngineResponse() {
        StartStopEngineResponse response = new StartStopEngineResponse();
        response.setStatus(actionResult.getStatus().toStartStopEngineStatus());
        return response;
    }
}
