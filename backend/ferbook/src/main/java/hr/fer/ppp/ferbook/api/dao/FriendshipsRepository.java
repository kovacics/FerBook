package hr.fer.ppp.ferbook.api.dao;

import hr.fer.ppp.ferbook.api.model.Friendship;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FriendshipsRepository extends JpaRepository<Friendship, Integer> {

    List<Friendship> findAllByFriendIDAndAcceptedInvitationAtNull(int friendID);

    List<Friendship> findAllByFriendIDAndAcceptedInvitationAtNotNull(int id);

    List<Friendship> findAllByUserIDAndAcceptedInvitationAtNotNull(int id);

    Friendship findByFriendIDAndUserID(int friendID, int userID);
}
