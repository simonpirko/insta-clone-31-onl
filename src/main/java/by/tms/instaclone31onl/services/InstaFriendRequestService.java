package by.tms.instaclone31onl.services;

import by.tms.instaclone31onl.core.interfaces.repositories.FriendRequestRepository;
import by.tms.instaclone31onl.core.interfaces.repositories.UserRepository;
import by.tms.instaclone31onl.core.interfaces.services.FriendRequestService;
import by.tms.instaclone31onl.core.models.dtos.UserShortDto;
import by.tms.instaclone31onl.core.models.entities.FriendRequest;
import by.tms.instaclone31onl.core.models.entities.User;

import java.util.List;
import java.util.UUID;

public class InstaFriendRequestService implements FriendRequestService {

    private final FriendRequestRepository friendRequestRepository;
    private final UserRepository userRepository;

    public InstaFriendRequestService(FriendRequestRepository friendRequestRepository, UserRepository userRepository) {
        this.friendRequestRepository = friendRequestRepository;
        this.userRepository = userRepository;
    }

    @Override
    public List<UserShortDto> getReceiverRequests(UUID userId) {
        List<FriendRequest> requests = friendRequestRepository.getAllBy(r -> r.getReceiverId().equals(userId));
        List<UUID> requesterIds = requests.stream().map(FriendRequest::getRequesterId).toList();

        return userRepository.getAllBy(u -> requesterIds.contains(u.getId()))
                .stream()
                .map(u -> new UserShortDto(u.getId(), u.getNickname(), u.getPhotos())).toList();
    }

    @Override
    public List<UserShortDto> getRequesterRequests(UUID userId) {
        List<FriendRequest> requests = friendRequestRepository.getAllBy(r -> r.getRequesterId().equals(userId));
        List<UUID> receiverIds = requests.stream().map(FriendRequest::getReceiverId).toList();

        return userRepository.getAllBy(u -> receiverIds.contains(u.getId()))
                .stream()
                .map(u -> new UserShortDto(u.getId(), u.getNickname(), u.getPhotos())).toList();
    }

    @Override
    public List<UUID> addRequest(UUID requesterId, UUID receiverId) {
        return friendRequestRepository.insert(List.of(new FriendRequest(null, requesterId, receiverId, null)));
    }

    @Override
    public List<UUID> removeRequest(UUID requesterId, UUID receiverId) {
        return friendRequestRepository
                .delete(u -> u.getRequesterId().equals(requesterId) && u.getReceiverId().equals(receiverId));
    }

    @Override
    public boolean acceptRequest(UUID requesterId, UUID receiverId) {
        try {
            List<User> users = userRepository.getAllBy(u -> u.getId().equals(receiverId) || u.getId().equals(requesterId));
            for (User user : users) {
                user.addFriend(user.getId().equals(requesterId) ? receiverId : requesterId);
                userRepository.update(user);
            }
            friendRequestRepository.delete(u -> u.getRequesterId().equals(requesterId) && u.getReceiverId().equals(receiverId));
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
