package com.ntnt.highblog.gateway.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;

public class RefreshTokenGrantTypeReq {
    @JsonProperty("grant_type")
    private String grantType;

    @JsonProperty("refresh_token")
    private String refreshToken;

    private String scope;

    public RefreshTokenGrantTypeReq(final String grantType, final String refreshToken, final String scope) {
        this.grantType = grantType;
        this.refreshToken = refreshToken;
        this.scope = scope;
    }

    public String getGrantType() {
        return grantType;
    }

    public void setGrantType(final String grantType) {
        this.grantType = grantType;
    }

    public String getRefreshToken() {
        return refreshToken;
    }

    public void setRefreshToken(final String refreshToken) {
        this.refreshToken = refreshToken;
    }

    public String getScope() {
        return scope;
    }

    public void setScope(final String scope) {
        this.scope = scope;
    }
}
