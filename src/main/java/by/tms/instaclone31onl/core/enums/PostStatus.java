package by.tms.instaclone31onl.core.enums;

public enum PostStatus {
    DRAW(0),
    PUBLISHED(1),
    UNPUBLISHED(2);

    private final int status;
    PostStatus(int status){
        this.status = status;
    }

    public int getStatusValue() {
        return status;
    }

    @Override
    public String toString() {
        return String.valueOf(this.status);
    }

    public static PostStatus getStatusByValue(int value) {
        for (PostStatus status : PostStatus.values()) {
            if (status.getStatusValue() == value) {
                return status;
            }
        }
        throw new EnumConstantNotPresentException(PostStatus.class,"%d not found".formatted(value));
    }
}
