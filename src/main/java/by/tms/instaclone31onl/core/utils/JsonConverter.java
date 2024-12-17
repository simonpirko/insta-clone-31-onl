package by.tms.instaclone31onl.core.utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class JsonConverter {
    public static <T> String serialize(T obj)
    {
        Gson gson = new GsonBuilder().create();
        return gson.toJson(obj);
    }

    public static <T> T deserialize(String json, Class<T> tClass) {
        Gson gson = new GsonBuilder().create();
        return (T)gson.fromJson(json, tClass);
    }
}
