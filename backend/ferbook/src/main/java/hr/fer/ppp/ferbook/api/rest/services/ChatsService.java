package hr.fer.ppp.ferbook.api.rest.services;

import hr.fer.ppp.ferbook.api.dao.ChatsRepository;
import hr.fer.ppp.ferbook.api.model.Chat;
import hr.fer.ppp.ferbook.api.model.ChatUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ChatsService {

    @Autowired
    ChatsRepository chatsRepository;

    @Autowired
    ChatUsersService chatUsersService;

    @Autowired
    UsersService usersService;


    public List<Chat> getChats() {
        return chatsRepository.findAll();
    }

    public Chat getChat(int id) {
        return chatsRepository.findById(id).orElse(null);
    }

    public void deleteChat(int id) {
        chatsRepository.deleteById(id);
    }

    public Chat createChat(String name, List<Integer> userIds) {
        Chat chat = new Chat(name);
        chatsRepository.save(chat);

        userIds.forEach(t -> chatUsersService.createChatUser(new ChatUser(chat, usersService.getUserByID(t))));
        return chat;
    }

    public void replaceChat(int id, Chat chat) {
        chat.setID(id);
        chatsRepository.save(chat);
    }
}
    
