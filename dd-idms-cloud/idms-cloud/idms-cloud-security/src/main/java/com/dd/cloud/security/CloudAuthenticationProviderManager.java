package com.dd.cloud.security;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.ProviderManager;

import java.util.List;


public class CloudAuthenticationProviderManager extends ProviderManager {
    public CloudAuthenticationProviderManager(List<AuthenticationProvider> providers) {
        super(providers);
    }
}
