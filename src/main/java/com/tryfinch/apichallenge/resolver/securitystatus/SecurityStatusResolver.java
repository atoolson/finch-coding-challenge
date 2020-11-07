package com.tryfinch.apichallenge.resolver.securitystatus;

public interface SecurityStatusResolver {
    boolean supports(String id);
    SecurityStatus getSecurityStatus(String id);
}
