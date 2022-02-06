package com.ntnt.highblog.payment.api.external.user;

import com.ntnt.highblog.payment.bloc.DonationBloc;
import com.ntnt.highblog.payment.model.dto.request.DonationReq;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1/user/donations")
public class DonationController {

    private final DonationBloc donationBloc;

    public DonationController(final DonationBloc donationBloc) {
        this.donationBloc = donationBloc;
    }

    @PostMapping
    public ResponseEntity<?> donate(@RequestBody @Valid final DonationReq donationReq) {

        donationBloc.donate(donationReq);

        return ResponseEntity.noContent().build();
    }
}
