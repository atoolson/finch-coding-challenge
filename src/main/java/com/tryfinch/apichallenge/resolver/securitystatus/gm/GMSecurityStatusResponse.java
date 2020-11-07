package com.tryfinch.apichallenge.resolver.securitystatus.gm;

import com.tryfinch.apichallenge.resolver.securitystatus.DoorLockStatus;
import com.tryfinch.apichallenge.resolver.securitystatus.SecurityStatus;
import lombok.Data;

@Data
public class GMSecurityStatusResponse {
    private String service;
    private String status;
    private GMSecurityStatusResponseData data;

    public SecurityStatus toSecurityStatus() {
        SecurityStatus status = new SecurityStatus();
        DoorLockStatus doorLockStatus = new DoorLockStatus();

        for (GMSecurityStatusResponseDataDoor value : data.getDoors().getValues()) {
            if ("frontLeft".equalsIgnoreCase(value.getLocation().getValue())) {
                doorLockStatus.setFrontLeftLocked(value.getLocked().getBoolean());

            } else if ("frontRight".equalsIgnoreCase(value.getLocation().getValue())) {
                doorLockStatus.setFrontRightLocked(value.getLocked().getBoolean());

            } else if ("backLeft".equalsIgnoreCase(value.getLocation().getValue())) {
                doorLockStatus.setBackLeftLocked(value.getLocked().getBoolean());

            } else if ("backRight".equalsIgnoreCase(value.getLocation().getValue())) {
                doorLockStatus.setBackRightLocked(value.getLocked().getBoolean());
            }
        }

        status.setDoorLockStatus(doorLockStatus);
        return status;
    }
}
