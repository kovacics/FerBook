package hr.fer.ppp.ferbook.api.rest.services;

import hr.fer.ppp.ferbook.api.dao.ChatMessagesRepository;
import hr.fer.ppp.ferbook.chat.ChatMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ChatMessageService {

    @Autowired
    ChatMessagesRepository chatMessagesRepository;


    public List<ChatMessage> getChatMessages() {
        return chatMessagesRepository.findAll();
    }

    public ChatMessage getChatMessage(int id) {
        return chatMessagesRepository.findById(id).orElse(null);
    }

    public void createChatMessage(ChatMessage chatMessage) {
        chatMessagesRepository.save(chatMessage);
    }

    public void deleteChatMessage(int id) {
        chatMessagesRepository.deleteById(id);
    }

    public void replaceChatMessage(int id, ChatMessage chatMessage) {
        chatMessage.setID(id);
        chatMessagesRepository.save(chatMessage);
    }
}
