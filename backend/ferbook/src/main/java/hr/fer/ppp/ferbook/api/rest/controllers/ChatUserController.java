package hr.fer.ppp.ferbook.api.rest.controllers;

import hr.fer.ppp.ferbook.api.model.ChatUser;
import hr.fer.ppp.ferbook.api.rest.services.ChatUsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/chatUsers")
public class ChatUserController {

    @Autowired
    private ChatUsersService chatUsersService;

    @PutMapping("/{id}")
    public void replaceChatUser(@RequestBody ChatUser chatUser, @PathVariable int id) {
        chatUsersService.replaceChatUser(id, chatUser);
    }

    @GetMapping("")
    public List<ChatUser> getChatUsers() {
        return chatUsersService.getChatUsers();
    }

    @GetMapping("/{id}")
    public ChatUser getChatUser(@PathVariable int id) {
        return chatUsersService.getChatUser(id);
    }

    @DeleteMapping("/{id}")
    public void deleteChatUser(@PathVariable int id) {
        chatUsersService.deleteChatUser(id);
    }

    @PostMapping("")
    public void createChatUser(@RequestBody ChatUser chatUser) {
        chatUsersService.createChatUser(chatUser);
    }
}
