package by.tms.instaclone31onl.core.models.dto;

import java.util.UUID;

public class Ticket {
    private final UUID userId;
    private final String userName;
    private final String email;

    public Ticket(final UUID userId, final String userName, final String email) {
        this.userId = userId;
        this.userName = userName;
        this.email = email;
    }
    public UUID getUserId() {
        return userId;
    }
    public String getUserName() {
        return userName;
    }
    public String getEmail() {
        return email;
    }
}
