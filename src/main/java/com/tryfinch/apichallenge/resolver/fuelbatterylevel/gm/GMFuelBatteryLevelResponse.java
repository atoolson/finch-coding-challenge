package com.tryfinch.apichallenge.resolver.fuelbatterylevel.gm;

import com.tryfinch.apichallenge.resolver.fuelbatterylevel.FuelBatteryLevel;
import lombok.Data;

@Data
public class GMFuelBatteryLevelResponse {
    private String service;
    private String status;
    private GMFuelBatteryLevelData data;

    public FuelBatteryLevel toFuelBatteryLevel() {
        FuelBatteryLevel level = new FuelBatteryLevel();
        level.setBatteryLevel(data.getBatteryLevel().getFloat());
        level.setTankLevel(data.getTankLevel().getFloat());
        return level;
    }
}
