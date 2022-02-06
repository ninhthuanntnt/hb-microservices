package com.ntnt.highblog.payment.bloc;

import com.ntnt.highblog.payment.helper.SecurityHelper;
import com.ntnt.highblog.payment.model.dto.response.WalletRes;
import com.ntnt.highblog.payment.service.WalletService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

@Slf4j
@Component
public class WalletCrudBloc {
    private final WalletService walletService;

    public WalletCrudBloc(final WalletService walletService) {
        this.walletService = walletService;
    }

    @Transactional(readOnly = true)
    public WalletRes getBalance() {
        Long userId = SecurityHelper.getCurrentUserId();
        log.info("get balance of userId=#{}", userId);
        BigDecimal balance = walletService.getBalance(userId).getBalance();
        return new WalletRes(balance);
    }
}
