package com.ntnt.highblog.payment.helper;

import java.util.UUID;

public class CodeHelper {

    public static String generateUUID() {
        return UUID.randomUUID().toString().replace("-", "");
    }
}
