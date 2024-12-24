package by.tms.instaclone31onl.core.models.entities;

import by.tms.instaclone31onl.core.annotations.Entity;
import by.tms.instaclone31onl.core.utils.JsonConverter;
import com.opencsv.bean.CsvBindByPosition;
import lombok.Builder;
import lombok.experimental.SuperBuilder;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@SuperBuilder
@Entity(name="users", directories = "\\resources")
public class User extends BaseEntity {
    @CsvBindByPosition(position = 1)
    private final String login;
    @CsvBindByPosition(position = 2)
    private final String hash;
    @CsvBindByPosition(position = 3)
    private final String nickname;
    @CsvBindByPosition(position = 4)
    private final List<UUID> friendIds;
    @CsvBindByPosition(position = 5)
    private final List<UUID> blackList;
    @CsvBindByPosition(position = 6)
    private final List<String> photos;

    public User(UUID id,
                String login,
                String hash,
                String nickname,
                List<UUID> friendIds,
                List<UUID> blackList,
                List<String> photos) {
        super(id);
        this.login = login;
        this.hash = hash;
        this.nickname = nickname;
        this.friendIds = Optional.ofNullable(friendIds).orElse(new ArrayList<>());
        this.blackList = Optional.ofNullable(blackList).orElse(new ArrayList<>());
        this.photos = Optional.ofNullable(photos).orElse(new ArrayList<>());
    }

    public List<UUID> getBlackList() {
        return blackList;
    }
    public List<UUID> getFriendIds() {
        return friendIds;
    }
    public String getNickname() {
        return nickname;
    }
    public String getHash() {
        return hash;
    }
    public String getLogin() {
        return login;
    }
    public List<String> getPhotos() {
        return photos;
    }

    @Override
    public String[] getLine() {
        return new String[]{
                String.valueOf(id),
                login,
                hash,
                nickname,
                JsonConverter.serialize(friendIds),
                JsonConverter.serialize(blackList),
                JsonConverter.serialize(photos)
        };
    }

    public static User fromLine(String[] line){
        return new User(UUID.fromString(line[0]),
                line[1],
                line[2],
                line[3],
                (List<UUID>)JsonConverter.deserialize(line[4], List.class),
                (List<UUID>)JsonConverter.deserialize(line[5], List.class),
                (List<String>)JsonConverter.deserialize(line[6], List.class)
                );
    }
}
