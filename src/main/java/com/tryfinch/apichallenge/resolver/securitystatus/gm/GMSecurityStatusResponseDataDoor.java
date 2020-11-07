package com.tryfinch.apichallenge.resolver.securitystatus.gm;

import com.tryfinch.apichallenge.resolver.vehicleinfo.gm.TypeValuePair;
import lombok.Data;

@Data
public class GMSecurityStatusResponseDataDoor {
    private TypeValuePair location;
    private TypeValuePair locked;
}
