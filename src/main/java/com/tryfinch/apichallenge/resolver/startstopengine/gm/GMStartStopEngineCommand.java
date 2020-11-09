package com.tryfinch.apichallenge.resolver.startstopengine.gm;

import com.tryfinch.apichallenge.resolver.startstopengine.StartStopEngineCommand;

public enum GMStartStopEngineCommand {
    START_VEHICLE, STOP_VEHICLE;

    public static GMStartStopEngineCommand fromStartStopEngineCommand(StartStopEngineCommand command) {
        switch (command) {
            case START:
                return START_VEHICLE;
            case STOP:
                return STOP_VEHICLE;
            default:
                throw new IllegalArgumentException(command + " cannot be converted to a GMStartStopEngineCommand");
        }
    }
}
