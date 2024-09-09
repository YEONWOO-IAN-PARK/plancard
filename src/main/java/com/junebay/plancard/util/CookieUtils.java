package com.junebay.plancard.util;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.util.SerializationUtils;

import javax.swing.text.html.Option;
import java.util.Arrays;
import java.util.Base64;
import java.util.Optional;

/**
 * @author : IAN
 * @date : 2024-09-09
 * @description :
 */
public class CookieUtils {

    // 특정 이름의 쿠키를 HttpServletRequest에서 찾아 Optional로 반환하는 메서드
    public static Optional<Cookie> getCookie(HttpServletRequest request, String name) {
        Cookie[] cookies = request.getCookies();
        if (cookies != null && cookies.length > 0) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals(name)) {
                    return Optional.of(cookie);
                }
            }
        }
        return Optional.empty();
    }

    // 특정 이름의 쿠키 값을 HttpServletRequest에서 읽어 Optional로 반환하는 메서드
    public static Optional<String> readServletCookie(HttpServletRequest request, String name) {
        return Arrays.stream(request.getCookies())
                .filter(cookie -> name.equals(cookie.getName()))
                .map(Cookie::getValue)
                .findAny();
    }

    // 새로운 쿠키를 생성하여 HttpServletResponse에 추가하는 메서드
    public static void addCookie(HttpServletResponse response, String name, String value, int maxAge) {
        Cookie cookie = new Cookie(name, value);
        cookie.setPath("/");
        cookie.setHttpOnly(true);
        cookie.setMaxAge(maxAge);
        response.addCookie(cookie);
    }

    // 특정 이름의 쿠키를 삭제하는 메서드
    public static void deleteCookie(HttpServletRequest request, HttpServletResponse response, String name) {
        Cookie[] cookies = request.getCookies();
        if (cookies != null && cookies.length > 0) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals(name)) {
                    cookie.setValue("");
                    cookie.setPath("/");
                    cookie.setMaxAge(0);
                    response.addCookie(cookie);
                }
            }
        }
    }

    // 객체를 직렬화하여 Base64로 인코딩하는 메서드
    public static String serialize(Object object) {
        // 객체를 직렬화한 후 Base64로 인코딩하여 문자열로 반환
        return Base64.getUrlEncoder().encodeToString(SerializationUtils.serialize(object));
    }

    // 쿠키의 값을 객체로 역직렬화하는 메서드
    public static <T> T deserialize(Cookie cookie, Class<T> cls) {
        // 쿠키의 값을 Base64로 디코딩한 후 역직렬화하여 객체로 변환
        return cls.cast(SerializationUtils.deserialize(Base64.getUrlDecoder().decode(cookie.getValue())));
    }
}
