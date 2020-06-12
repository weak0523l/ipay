package com.example.ipay.bean;

/**
 * @author zhangjun
 */
public class UserContext {
    private static final ThreadLocal<String> LOCAL = new ThreadLocal<>();

    public static void set(String id) {
        LOCAL.set(id);
    }

    public static String get() {
        return LOCAL.get();
    }

    public static void remove() {
        LOCAL.remove();
    }
}
