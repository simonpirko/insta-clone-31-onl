package by.tms.instaclone31onl.core.enums;

import java.util.Stack;

public enum PostStatus {
    PUBLISHED("pub"),
    UNPUBLISHED("unpub");

    private final String status;
    PostStatus(String status){
        this.status = status;
    }

    public String getStatusValue() {
        return status;
    }

    public static PostStatus getStatusByValue(String value) {
        for (PostStatus status : PostStatus.values()) {
            if (status.getStatusValue().equals(value)) {
                return status;
            }
        }
        throw new EnumConstantNotPresentException(PostStatus.class,"%s not found".formatted(value));
    }
}
