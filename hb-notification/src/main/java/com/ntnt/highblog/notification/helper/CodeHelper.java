package com.ntnt.highblog.notification.helper;

import java.util.UUID;

public class CodeHelper {

    public static String generateUUID() {
        return UUID.randomUUID().toString().replace("-", "");
    }
}
