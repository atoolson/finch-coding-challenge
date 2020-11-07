package com.tryfinch.apichallenge.resolver.securitystatus.gm;

import lombok.Data;

/**
 * while this class looks identical to {@link com.tryfinch.apichallenge.resolver.vehicleinfo.gm.GMVehicleInfoRequest}
 * we want to keep them separate in case one gets new fields added in the future.
  */
@Data
public class GMSecurityStatusRequest {
    private String id;
    private String responseType = "JSON";
}
