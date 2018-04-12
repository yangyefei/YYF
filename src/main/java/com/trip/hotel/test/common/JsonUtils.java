package com.trip.hotel.test.common;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.commons.io.FileUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.io.File;
import java.io.IOException;
import java.util.*;

public class JsonUtils {
    private static final Log logger = LogFactory.getLog(JsonUtils.class);

    public static Iterator<Object[]> readJson(String fileName) {

        try {
            File file = new File(new File("./").getCanonicalPath() + "/testdata/android/" + fileName);
            logger.info("Json File: " + file.getAbsolutePath());
            String jsonText = FileUtils.readFileToString(file, "utf-8");
            JSONArray jsonArray = JSONArray.fromObject(jsonText);
            int size = jsonArray == null ? 0 : jsonArray.size();
            if (size <= 0) {
                return null;
            }
            List<Object[]> result = new ArrayList<>(size);
            for (int i = 0; i < size; i++) {
                Map<String, String> map = new HashMap<>();
                JSONObject item = (JSONObject) jsonArray.get(i);
                Set keys = item.keySet();
                for (Object key : keys) {
                    String value = item.optString((String) key);
                    map.put((String) key, value);
                }
                result.add(new Object[]{map});
            }
            return result.iterator();
        } catch (IOException e) {
            logger.error(e.getMessage(), e);
        }
        return null;
    }
}
