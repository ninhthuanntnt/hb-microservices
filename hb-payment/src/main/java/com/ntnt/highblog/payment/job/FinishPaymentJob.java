package com.ntnt.highblog.payment.job;

import com.ntnt.highblog.payment.bloc.UserTransactionBloc;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class FinishPaymentJob {

    private final UserTransactionBloc userTransactionBloc;

    public FinishPaymentJob(final UserTransactionBloc userTransactionBloc) {
        this.userTransactionBloc = userTransactionBloc;
    }

    @Scheduled(fixedDelay = 10000L)
    public void updateUserTransactionStatus() {
        userTransactionBloc.updateUserTransactionStatusFromJob();
    }
}
