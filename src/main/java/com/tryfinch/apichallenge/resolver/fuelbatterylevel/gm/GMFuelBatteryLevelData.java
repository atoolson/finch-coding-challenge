package com.tryfinch.apichallenge.resolver.fuelbatterylevel.gm;

import com.tryfinch.apichallenge.resolver.vehicleinfo.gm.TypeValuePair;
import lombok.Data;

@Data
public class GMFuelBatteryLevelData {
    private TypeValuePair tankLevel;
    private TypeValuePair batteryLevel;
}
