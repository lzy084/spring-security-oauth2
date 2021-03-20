package com.dd.oauth.server.configuration;

import java.util.List;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.ProviderManager;

public class OAuth2AuthorizationManager extends ProviderManager {
    public OAuth2AuthorizationManager(List<AuthenticationProvider> providers) {
        super(providers);
    }
}
