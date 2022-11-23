package util;

import com.google.gson.Gson;

public class JsonParser {
    private static Gson gson = new Gson();

    public static String objectToJson(Object object) {
        return gson.toJson(object);
    }

    public static Object jsonToObject(String json) {
        return gson.fromJson(json, Object.class);
    }
}
