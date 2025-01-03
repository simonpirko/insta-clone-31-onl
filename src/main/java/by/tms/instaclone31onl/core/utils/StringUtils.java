package by.tms.instaclone31onl.core.utils;

import java.util.Objects;

public class StringUtils {

    public static boolean isNotEmpty(String str) {

        return Objects.nonNull(str) && !str.isEmpty();
    }

    public static boolean isEmpty(String str) {
        return Objects.isNull(str) || str.isEmpty();
    }
}
