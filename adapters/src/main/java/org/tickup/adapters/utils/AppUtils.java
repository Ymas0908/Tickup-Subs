package org.tickup.adapters.utils;

import jakarta.servlet.http.HttpServletRequest;

public class AppUtils {
    public static String getIpAddress(HttpServletRequest request) {
        String ipAddress = request.getHeader("X-Forwarded-For");
        if (ipAddress == null || ipAddress.isEmpty() || "unknown".equalsIgnoreCase(ipAddress)) {
            ipAddress = request.getRemoteAddr();
        }
        return ipAddress;
    }

    public static String troncatCodeClient(String codeClient) {

        if (codeClient == null || codeClient.length() < 4) {
            return "****"; // fallback sécurité
        }

        int length = codeClient.length();
        String start = codeClient.substring(0, 2);
        String end = codeClient.substring(length - 2);

        return start + "*".repeat(length - 4) + end;
    }

}
