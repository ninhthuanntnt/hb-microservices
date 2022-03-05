package com.ntnt.highblog.uaa.api.client;

import com.ntnt.highblog.uaa.model.dto.request.WalletCreateReq;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Component
@FeignClient(name = "hb-payment")
public interface HbPaymentClient {

    @PostMapping(value = "/api/v1/internal/wallets")
    ResponseEntity<Void> createWallet(@RequestBody final WalletCreateReq walletCreateReq);
}
