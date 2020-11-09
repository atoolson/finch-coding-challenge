package com.tryfinch.apichallenge.resolver.fuelbatterylevel;

public interface FuelBatteryLevelResolver {
    boolean supports(String id);
    FuelBatteryLevel getFuelBatteryLevel(String id);
}
