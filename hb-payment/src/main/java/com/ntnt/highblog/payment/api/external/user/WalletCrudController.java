package com.ntnt.highblog.payment.api.external.user;

import com.ntnt.highblog.payment.bloc.WalletCrudBloc;
import com.ntnt.highblog.payment.model.dto.response.WalletRes;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/user/wallets")
public class WalletCrudController {

    private final WalletCrudBloc walletCrudBloc;

    public WalletCrudController(final WalletCrudBloc walletCrudBloc) {
        this.walletCrudBloc = walletCrudBloc;
    }

    @GetMapping
    public ResponseEntity<?> getBalance() {
        WalletRes balance = walletCrudBloc.getBalance();
        return ResponseEntity.ok(balance);
    }
}