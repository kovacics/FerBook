package hr.fer.ppp.ferbook.api.dao;

import hr.fer.ppp.ferbook.api.model.Chat;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChatsRepository extends JpaRepository<Chat, Integer> {
}
