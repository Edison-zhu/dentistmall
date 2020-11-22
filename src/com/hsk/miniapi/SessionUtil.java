package com.hsk.miniapi;

/**
 * author: yangfeng
 * time: 2020/4/15 14:00
 */

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

public class SessionUtil {

    private static Map<String, String> SESSIONMAP = new HashMap<String, String>();
    private static Map<String, String> sessionLink = new HashMap<String, String>();

    public static String getSession(String account) {
        return SESSIONMAP.get(account);
    }

    public static void setSession(String account, String localSession) {
        SESSIONMAP.put(account, localSession);
    }

    public static void remove(String account) {
        SESSIONMAP.remove(account);
    }

    public static String getLocalSessionId(String allSessionId) {
        return sessionLink.get(allSessionId);
    }

    public static String getLocalAccount(String sessionId) {
        return sessionLink.get(sessionId);
    }

    public static void setLink(String allSessionId, String account) {
        sessionLink.put(allSessionId, account);
    }

    public static void removeL(String sessionId, String account) {
        sessionLink.remove(sessionId);
    }
}