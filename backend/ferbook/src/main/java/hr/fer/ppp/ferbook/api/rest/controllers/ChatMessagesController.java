package hr.fer.ppp.ferbook.api.rest.controllers;

import hr.fer.ppp.ferbook.api.rest.services.ChatMessageService;
import hr.fer.ppp.ferbook.chat.ChatMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/chatMessages")
public class ChatMessagesController {

    @Autowired
    private ChatMessageService chatMessageService;

    @PutMapping("/{id}")
    public void replaceChatMessage(@RequestBody ChatMessage chatMessage, @PathVariable int id) {
        chatMessageService.replaceChatMessage(id, chatMessage);
    }

    @GetMapping("")
    public List<ChatMessage> getChatMessages() {
        return chatMessageService.getChatMessages();
    }

    @GetMapping("/{id}")
    public ChatMessage getChatMessage(@PathVariable int id) {
        return chatMessageService.getChatMessage(id);
    }

    @DeleteMapping("/{id}")
    public void deleteChatMessage(@PathVariable int id) {
        chatMessageService.deleteChatMessage(id);
    }

    @PostMapping("")
    public void createChatMessage(@RequestBody ChatMessage chatMessage) {
        chatMessageService.createChatMessage(chatMessage);
    }
}
