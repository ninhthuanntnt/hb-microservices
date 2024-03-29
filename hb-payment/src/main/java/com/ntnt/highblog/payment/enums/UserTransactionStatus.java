package com.ntnt.highblog.payment.enums;

public enum UserTransactionStatus {
    CREATED, IN_PROGRESS, FINISHED, FAILED, CANCELED;

    public boolean isFinalStatus() {
        return this == FINISHED || this == FAILED || this == CANCELED;
    }
}
