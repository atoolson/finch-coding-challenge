package com.tryfinch.apichallenge.resolver.vehicleinfo.gm;

import lombok.Data;

@Data
public class GMVehicleInfoRequest {
    private String id;
    private String responseType = "JSON";
}
