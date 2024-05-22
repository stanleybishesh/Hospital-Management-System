package com.example.hms.common;

import java.util.List;
import java.util.regex.Pattern;

public final class Preconditions {
    public static <T> boolean checkNotNull(T reference) {
        if (reference == null) {
            return false;
        }
        return true;
    }

    public static <T> boolean checkNull(T reference) {
        if (reference == null) {
            return true;
        }
        return false;
    }

    public static <T> boolean checkNotNull(List<T> reference) {
        if (reference != null && !reference.isEmpty()) {
            return true;
        }
        return false;
    }

    public static boolean checkBlank(String reference) {
        if (reference == null || reference.trim().equalsIgnoreCase("")) {
            return true;
        }
        return false;
    }

    public static boolean checkNotBlank(String reference) {
        if (reference == null || reference.trim().equalsIgnoreCase("")) {
            return false;
        }
        return true;
    }

    public static boolean checkUUIDFormat(String uuid) {
        if (uuid == null || uuid.trim().equalsIgnoreCase("")) {
            return false;
        }
        Pattern p = Pattern.compile("[0-9a-f]{8}-[0-9a-f]{4}-[0-9a-f]{4}-[0-9a-f]{4}-[0-9a-f]{12}$");

        return p.matcher(uuid).matches() ? true : false;

    }
}
