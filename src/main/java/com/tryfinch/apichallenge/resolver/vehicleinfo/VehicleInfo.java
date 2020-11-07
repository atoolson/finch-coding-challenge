package com.tryfinch.apichallenge.resolver.vehicleinfo;

import com.tryfinch.apichallenge.pojos.CarType;
import com.tryfinch.apichallenge.pojos.DriveTrain;
import lombok.Data;

@Data
public class VehicleInfo {
    private String vin;
    private String color;
    private CarType carType;
    private DriveTrain driveTrain;
}
