package hr.fer.ppp.ferbook.api.rest.services;

import hr.fer.ppp.ferbook.api.dao.ChatUserRepository;
import hr.fer.ppp.ferbook.api.model.Chat;
import hr.fer.ppp.ferbook.api.model.ChatUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ChatUsersService {

    @Autowired
    ChatUserRepository chatUserRepository;


    public List<ChatUser> getChatUsers() {
        return chatUserRepository.findAll();
    }

    public ChatUser getChatUser(int id) {
        return chatUserRepository.findById(id).orElse(null);
    }

    public void createChatUser(ChatUser chatUser) {
        chatUserRepository.save(chatUser);
    }

    public void deleteChatUser(int id) {
        chatUserRepository.deleteById(id);
    }

    public void replaceChatUser(int id, ChatUser chatUser) {
        chatUser.setID(id);
        chatUserRepository.save(chatUser);
    }

    public List<Chat> getChatsByUserId(int userId) {
        List<Chat> chats = chatUserRepository.findByUser_ID(userId).stream()
                .map(ChatUser::getChat)
                .collect(Collectors.toList());

        Comparator<Chat> comparator = Comparator.comparing(Chat::getCreatedAt);
        return chats.stream()
                .sorted(comparator.reversed())
                .distinct()
                .collect(Collectors.toList());
    }
}
