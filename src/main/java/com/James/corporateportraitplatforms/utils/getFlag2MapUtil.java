package com.James.corporateportraitplatforms.utils;

import java.util.Map;

public class getFlag2MapUtil {

    public static Integer getFlag(Map<Integer, Integer> map, String intKey) {
        return map.get(Integer.valueOf(intKey));
    }
}
