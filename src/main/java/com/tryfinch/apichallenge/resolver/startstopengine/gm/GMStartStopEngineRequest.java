package com.tryfinch.apichallenge.resolver.startstopengine.gm;

import lombok.Data;

@Data
public class GMStartStopEngineRequest {
    private String id;
    private GMStartStopEngineCommand command;
    private String responseType = "JSON";
}
