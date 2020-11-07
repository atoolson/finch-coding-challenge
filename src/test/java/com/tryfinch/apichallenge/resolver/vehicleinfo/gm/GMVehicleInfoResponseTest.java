package com.tryfinch.apichallenge.resolver.vehicleinfo.gm;

import com.tryfinch.apichallenge.pojos.CarType;
import com.tryfinch.apichallenge.pojos.DriveTrain;
import com.tryfinch.apichallenge.resolver.vehicleinfo.VehicleInfo;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GMVehicleInfoResponseTest {

    @Test
    void toVehicleInfo() {
        GMVehicleInfoResponse response = new GMVehicleInfoResponse();
        response.setStatus("200");

        GMVehicleInfoResponseData data = new GMVehicleInfoResponseData();
        data.setVin(new TypeValuePair("String", "123123abcdef"));
        data.setColor(new TypeValuePair("String", "Metallic Silver"));
        data.setFourDoorSedan(new TypeValuePair("Boolean", "False"));
        data.setTwoDoorCoupe(new TypeValuePair("Boolean", "True"));
        data.setDriveTrain(new TypeValuePair("String", "v4"));
        response.setData(data);

        VehicleInfo info = response.toVehicleInfo();

        assertEquals(response.getData().getVin().getValue(), info.getVin());
        assertEquals(response.getData().getColor().getValue(), info.getColor());
        assertEquals(CarType.TWO_DOOR_COUPE, info.getCarType());
        assertEquals(DriveTrain.V4, info.getDriveTrain());
    }
}