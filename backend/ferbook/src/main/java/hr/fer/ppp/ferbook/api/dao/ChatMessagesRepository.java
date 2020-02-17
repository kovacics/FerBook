package hr.fer.ppp.ferbook.api.dao;

import hr.fer.ppp.ferbook.chat.ChatMessage;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChatMessagesRepository extends JpaRepository<ChatMessage, Integer> {
}
