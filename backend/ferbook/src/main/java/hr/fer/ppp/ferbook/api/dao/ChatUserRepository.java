package hr.fer.ppp.ferbook.api.dao;

import hr.fer.ppp.ferbook.api.model.ChatUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ChatUserRepository extends JpaRepository<ChatUser, Integer> {

    List<ChatUser> findByUser_ID(int userId);
}
