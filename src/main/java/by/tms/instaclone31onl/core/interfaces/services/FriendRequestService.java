package by.tms.instaclone31onl.core.interfaces.services;

import by.tms.instaclone31onl.core.models.dtos.UserShortDto;

import java.util.List;
import java.util.UUID;

public interface FriendRequestService {
    List<UserShortDto> getReceiverRequests(UUID userId);
    List<UserShortDto> getRequesterRequests(UUID userId);
    List<UUID> addRequest(UUID requesterId, UUID receiverId);
    List<UUID> removeRequest(UUID requesterId, UUID receiverId);
    boolean acceptRequest(UUID requesterId, UUID receiverId);
}
