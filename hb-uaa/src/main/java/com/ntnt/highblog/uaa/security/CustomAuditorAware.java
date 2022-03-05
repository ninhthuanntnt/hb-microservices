package com.ntnt.highblog.uaa.security;

import com.ntnt.highblog.uaa.constant.Constant;
import com.ntnt.highblog.uaa.helper.SecurityHelper;
import org.springframework.data.domain.AuditorAware;

import java.util.Optional;

public class CustomAuditorAware
    implements AuditorAware<String> {

    @Override
    public Optional<String> getCurrentAuditor() {
        return Optional.of(SecurityHelper.getNullableCurrentUsername().orElse(Constant.SYSTEM_ACCOUNT));
    }
}
