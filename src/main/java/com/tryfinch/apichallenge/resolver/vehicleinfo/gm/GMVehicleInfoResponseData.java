package com.tryfinch.apichallenge.resolver.vehicleinfo.gm;

import lombok.Data;

@Data
public class GMVehicleInfoResponseData {
    private TypeValuePair vin;
    private TypeValuePair color;
    private TypeValuePair fourDoorSedan;
    private TypeValuePair twoDoorCoupe;
    private TypeValuePair driveTrain;
}
