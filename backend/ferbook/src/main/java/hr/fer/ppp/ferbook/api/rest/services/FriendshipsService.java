package hr.fer.ppp.ferbook.api.rest.services;

import hr.fer.ppp.ferbook.api.dao.FriendshipsRepository;
import hr.fer.ppp.ferbook.api.model.Friendship;
import hr.fer.ppp.ferbook.api.model.User;
import hr.fer.ppp.ferbook.config.exception.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class FriendshipsService {

    @Autowired
    private FriendshipsRepository friendshipsRepository;

    @Autowired
    private UsersService usersService;

    public List<Friendship> getAllFriendships() {
        return friendshipsRepository.findAll();
    }

    public Friendship getFriendshipByID(int id) {
        return friendshipsRepository.findById(id).orElse(null);
    }

    public void replaceFriendship(int id, Friendship friendship) {
        friendship.setID(id);
        friendshipsRepository.save(friendship);
    }

    public void deleteFriendship(int id) {
        friendshipsRepository.deleteById(id);
    }

    public List<Friendship> getAllUnconfirmedFriendshipsByFriendID(int id) {
        return friendshipsRepository.findAllByFriendIDAndAcceptedInvitationAtNull(id);
    }

    public Friendship findByFriendIDAndUserID(int friendID, int userID) {
        return friendshipsRepository.findByFriendIDAndUserID(friendID, userID);
    }

    private List<Friendship> getAllConfirmedFriendshipsWithUserID(int id) {
        return friendshipsRepository.findAllByUserIDAndAcceptedInvitationAtNotNull(id);
    }

    private List<Friendship> getAllConfirmedFriendshipsWithFriendID(int id) {
        return friendshipsRepository.findAllByFriendIDAndAcceptedInvitationAtNotNull(id);
    }

    public List<User> getAllFriendsOfUserWithID(int id) {
        List<User> friends1 = getAllConfirmedFriendshipsWithFriendID(id)
                .stream()
                .map(Friendship::getUser)
                .collect(Collectors.toList());
        List<User> friends2 = getAllConfirmedFriendshipsWithUserID(id)
                .stream()
                .map(Friendship::getFriend)
                .collect(Collectors.toList());

        friends1.addAll(friends2);
        return friends1;
    }

    public Friendship createFriendship(int userId, int friendId) {
        Friendship existingFriendship = findByFriendIDAndUserID(friendId, userId);
        Friendship existingFriendshipRev = findByFriendIDAndUserID(userId, friendId);
        if (existingFriendship != null || existingFriendshipRev != null) {
            throw new BadRequestException("Friendship already exist.");
        }

        User user = usersService.getUserByID(userId);
        User friend = usersService.getUserByID(friendId);

        Friendship friendship = new Friendship(user, friend);
        friendshipsRepository.save(friendship);
        return friendship;
    }

    public void confirmFriendship(int friendshipID) {
        Friendship friendship = getFriendshipByID(friendshipID);
        if (friendship.getAcceptedInvitationAt() == null) {
            friendship.setAcceptedInvitationAt(new Timestamp(System.currentTimeMillis()));
        } else {
            throw new BadRequestException("Friendship already confirmed.");
        }

        friendshipsRepository.save(friendship);
    }

    public void rejectFriendship(int friendshipID) {
        if (getFriendshipByID(friendshipID).getAcceptedInvitationAt() != null) {
            throw new BadRequestException("Friendship already confirmed.");
        }

        friendshipsRepository.deleteById(friendshipID);
    }
}
