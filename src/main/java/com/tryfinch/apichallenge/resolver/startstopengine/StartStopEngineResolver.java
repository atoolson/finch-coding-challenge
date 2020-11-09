package com.tryfinch.apichallenge.resolver.startstopengine;

public interface StartStopEngineResolver {
    boolean supports(String id);
    StartStopEngineResponse startStopEngine(String id, StartStopEngineCommand command);
}
