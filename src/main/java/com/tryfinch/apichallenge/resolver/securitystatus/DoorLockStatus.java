package com.tryfinch.apichallenge.resolver.securitystatus;

import lombok.Data;

@Data
public class DoorLockStatus {
    private Boolean frontLeftLocked;
    private Boolean frontRightLocked;
    private Boolean backLeftLocked;
    private Boolean backRightLocked;
}
