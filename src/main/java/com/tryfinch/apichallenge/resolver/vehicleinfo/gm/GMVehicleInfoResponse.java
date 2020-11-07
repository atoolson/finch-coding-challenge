package com.tryfinch.apichallenge.resolver.vehicleinfo.gm;

import com.tryfinch.apichallenge.pojos.CarType;
import com.tryfinch.apichallenge.pojos.DriveTrain;
import com.tryfinch.apichallenge.resolver.vehicleinfo.VehicleInfo;
import lombok.Data;

@Data
public class GMVehicleInfoResponse {
    private String service;
    private String status;
    private GMVehicleInfoResponseData data;

    public VehicleInfo toVehicleInfo() {
        VehicleInfo info = new VehicleInfo();
        info.setVin(data.getVin().getValue());
        info.setColor(data.getColor().getValue());

        if (data.getFourDoorSedan() != null && data.getFourDoorSedan().getBoolean()) {
            info.setCarType(CarType.FOUR_DOOR_SEDAN);
        } else if (data.getTwoDoorCoupe() != null && data.getTwoDoorCoupe().getBoolean()) {
            info.setCarType(CarType.TWO_DOOR_COUPE);
        }

        if (data.getDriveTrain().getValue().equalsIgnoreCase("v4")) {
            info.setDriveTrain(DriveTrain.V4);
        } else if (data.getDriveTrain().getValue().equalsIgnoreCase("v6")) {
            info.setDriveTrain(DriveTrain.V6);
        } else if (data.getDriveTrain().getValue().equalsIgnoreCase("v8")) {
            info.setDriveTrain(DriveTrain.V8);
        }

        return info;
    }
}
