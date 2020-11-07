package com.tryfinch.apichallenge.resolver.securitystatus.gm;

import lombok.Data;

import java.util.List;

@Data
public class GMSecurityStatusResponseDataDoors {
    private String type;
    private List<GMSecurityStatusResponseDataDoor> values;
}
