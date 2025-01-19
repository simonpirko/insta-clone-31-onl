package by.tms.instaclone31onl.core.utils;

import by.tms.instaclone31onl.core.adapters.LocalDateTimeTypeAdapter;
import by.tms.instaclone31onl.core.adapters.PostStatusTypeAdapter;
import by.tms.instaclone31onl.core.enums.PostStatus;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.time.LocalDateTime;

public final class JsonConverter {
    public static <T> String serialize(T obj)
    {
        Gson gson = new GsonBuilder()
                .registerTypeAdapter(LocalDateTime.class, new LocalDateTimeTypeAdapter())
                .registerTypeAdapter(PostStatus.class, new PostStatusTypeAdapter())
                .create();
        return gson.toJson(obj);
    }

    public static <T> T deserialize(String json, Class<T> tClass) {
        Gson gson = new GsonBuilder()
                .registerTypeAdapter(LocalDateTime.class, new LocalDateTimeTypeAdapter())
                .registerTypeAdapter(PostStatus.class, new PostStatusTypeAdapter())
                .create();
        return (T)gson.fromJson(json, tClass);
    }
}
