package com.tryfinch.apichallenge.resolver.securitystatus;

import lombok.Data;

@Data
public class SecurityStatus {
    private DoorLockStatus doorLockStatus;

    // other security related fields would go below
    // perhaps alarm settings, dash cam info, etc
}
