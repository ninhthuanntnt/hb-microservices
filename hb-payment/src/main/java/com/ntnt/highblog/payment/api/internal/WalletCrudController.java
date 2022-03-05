package com.ntnt.highblog.payment.api.internal;

import com.ntnt.highblog.payment.model.dto.request.WalletCreateReq;
import com.ntnt.highblog.payment.service.WalletService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController("internalWalletCrudController")
@RequestMapping("/api/v1/internal/wallets")
public class WalletCrudController {
    private final WalletService walletService;

    public WalletCrudController(final WalletService walletService) {
        this.walletService = walletService;
    }

    @PostMapping
    public ResponseEntity<Void> createWallet(@RequestBody final WalletCreateReq walletCreateReq) {
        walletService.saveNew(walletCreateReq.getUserId());

        return ResponseEntity.ok().build();
    }
}
