package com.sddy.utils;

import java.util.List;

public class ArrayUtils {

    public static boolean isEmpty(List list) {
        return list == null || list.isEmpty();
    }

    public static <T> boolean  isEmpty(T[] array) {
        return array == null || array.length == 0;
    }
}
