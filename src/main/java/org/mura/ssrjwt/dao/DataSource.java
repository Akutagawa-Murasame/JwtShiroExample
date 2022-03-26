package org.mura.ssrjwt.dao;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Akutagawa Murasame
 * @date 2022/3/22 21:40
 */
public class DataSource {
    static Map<String, Map<String, String>> data = new HashMap<>();

    static {
        Map<String, String> temp1 = new HashMap<>(2, 1);
        Map<String, String> temp2 = new HashMap<>(2, 1);

        temp1.put("password", "smith123");
        temp1.put("role", "user");
        temp1.put("permission", "view");

        temp2.put("password", "danny123");
        temp2.put("role", "admin");
        temp2.put("permission", "view,edit");

        data.put("smith", temp1);
        data.put("danny", temp2);
    }

    public static Map<String, Map<String, String>> getData() {
        return data;
    }
}
