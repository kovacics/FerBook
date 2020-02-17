package hr.fer.ppp.ferbook.api.rest.controllers;

import hr.fer.ppp.ferbook.api.model.Chat;
import hr.fer.ppp.ferbook.api.rest.dtos.CreateChatDTO;
import hr.fer.ppp.ferbook.api.rest.services.ChatUsersService;
import hr.fer.ppp.ferbook.api.rest.services.ChatsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/chats")
public class ChatsController {

    @Autowired
    private ChatsService chatsService;

    @Autowired
    private ChatUsersService chatUsersService;

    @GetMapping("")
    public List<Chat> getChats() {
        return chatsService.getChats();
    }

    @GetMapping("/{id}")
    public Chat getChat(@PathVariable int id) {
        return chatsService.getChat(id);
    }

    @DeleteMapping("/{id}")
    public void deleteChat(@PathVariable int id) {
        chatsService.deleteChat(id);
    }

    @PutMapping("/{id}")
    public void replaceChat(@RequestBody Chat chat, @PathVariable int id) {
        chatsService.replaceChat(id, chat);
    }

    @PostMapping("")
    public ResponseEntity<Chat> createChat(@RequestBody CreateChatDTO chat) {
        return new ResponseEntity<>(chatsService.createChat(chat.getName(), chat.getUserIds()), HttpStatus.CREATED);
    }

    @GetMapping("/user/{id}")
    public List<Chat> getChatsByUserId(@PathVariable("id") int userId) {
        return chatUsersService.getChatsByUserId(userId);
    }
}
