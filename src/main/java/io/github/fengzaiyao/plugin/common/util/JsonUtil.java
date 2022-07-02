package io.github.fengzaiyao.plugin.common.util;

import com.google.gson.*;
import org.apache.commons.lang3.StringUtils;

import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

public class JsonUtil {

    private static Gson gson = new GsonBuilder().create();

    /**
     * 对象转Json
     */
    public static String toJson(Object obj) {
        return Objects.isNull(obj) ? null : gson.toJson(obj);
    }

    /**
     * Json转对象
     */
    public static <T> T toObject(String json, Class<T> clazz) {
        return validate(json, clazz) ? gson.fromJson(json, clazz) : null;
    }

    /**
     * Json转对象集合
     */
    public static <T> List<T> toObjectList(String json, Class<T> clazz) {
        if (!validate(json, clazz)) {
            return null;
        }
        List<T> ans = new LinkedList<>();
        JsonArray jsonArray = new JsonParser().parse(json).getAsJsonArray();
        for (JsonElement jsonElement : jsonArray) {
            ans.add(gson.fromJson(jsonElement, clazz));
        }
        return ans;
    }

    private static Boolean validate(String json, Class<?> clazz) {
        return !StringUtils.isBlank(json) && !Objects.isNull(clazz);
    }
}
