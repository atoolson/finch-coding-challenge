package com.tryfinch.apichallenge.resolver.startstopengine.gm;

import com.tryfinch.apichallenge.resolver.startstopengine.StartStopEngineStatus;

public enum GMStartStopEngineActionResultStatus {
    EXECUTED, FAILED;

    public StartStopEngineStatus toStartStopEngineStatus() {
        switch (this) {
            case EXECUTED:
                return StartStopEngineStatus.SUCCESS;
            case FAILED:
                return StartStopEngineStatus.FAILED;
            default:
                throw new IllegalArgumentException("Could not map " + this + " to a StartStopEngineStatus");
        }
    }
}
