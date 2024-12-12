package by.tms.instaclone31onl.core.utils;

import by.tms.instaclone31onl.core.adapters.LocalDateTypeAdapter;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.BufferedReader;
import java.time.LocalDate;

public final class JsonConverter {
    public static <T> String serialize(T obj)
    {
        Gson gson = new GsonBuilder()
                .registerTypeAdapter(LocalDate.class, new LocalDateTypeAdapter())
                .create();
        return gson.toJson(obj);
    }

    public static <T> T deserialize(String json, Class<T> tClass) {
        Gson gson = new GsonBuilder()
                .registerTypeAdapter(LocalDate.class, new LocalDateTypeAdapter())
                .create();
        return (T)gson.fromJson(json, tClass);
    }
    public static <T> T deserialize(BufferedReader buffer, Class<T> tClass) {
        Gson gson = new GsonBuilder()
                .registerTypeAdapter(LocalDate.class, new LocalDateTypeAdapter())
                .create();
        return (T)gson.fromJson(buffer, tClass);
    }
}