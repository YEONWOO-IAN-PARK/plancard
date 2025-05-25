package com.junebay.plancard.common.util;

/**
 * 공통함수 클래스
 */
public class CommonUtil {

    // Java 11 이상
    public static boolean isNullOrBlank(String str) {
        return str == null || str.isBlank();
    }
}
