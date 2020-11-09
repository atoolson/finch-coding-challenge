package com.tryfinch.apichallenge.resolver.fuelbatterylevel.gm;

import lombok.Data;

@Data
public class GMFuelBatteryLevelRequest {
    private String id;
    private String responseType = "JSON";
}
