package by.tms.instaclone31onl.core.adapters;

import by.tms.instaclone31onl.core.enums.PostStatus;
import com.google.gson.*;

import java.lang.reflect.Type;

public class PostStatusTypeAdapter implements JsonSerializer<PostStatus>, JsonDeserializer<PostStatus> {
    @Override
    public PostStatus deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
        return PostStatus.getStatusByValue(jsonElement.getAsString());
    }

    @Override
    public JsonElement serialize(PostStatus status, Type type, JsonSerializationContext jsonSerializationContext) {
        return new JsonPrimitive(status.getStatusValue());
    }

}