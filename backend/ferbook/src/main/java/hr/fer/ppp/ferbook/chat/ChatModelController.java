package hr.fer.ppp.ferbook.chat;

import hr.fer.ppp.ferbook.api.model.Chat;
import hr.fer.ppp.ferbook.api.model.User;
import hr.fer.ppp.ferbook.api.rest.dtos.AddUserDTO;
import hr.fer.ppp.ferbook.api.rest.dtos.CreateChatMessageDTO;
import hr.fer.ppp.ferbook.api.rest.services.ChatMessageService;
import hr.fer.ppp.ferbook.api.rest.services.ChatsService;
import hr.fer.ppp.ferbook.api.rest.services.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.stereotype.Controller;

@Controller
public class ChatModelController {

    @Autowired
    UsersService usersService;

    @Autowired
    ChatsService chatsService;

    @Autowired
    ChatMessageService chatMessageService;

    @MessageMapping("/chat.sendMessage/{chatId}")
    @SendTo("/topic/public/{chatId}")
    public ChatMessage sendMessage(@Payload CreateChatMessageDTO chatMessageDTO, SimpMessageHeaderAccessor headerAccessor, @DestinationVariable int chatId) {
        String username = (String) headerAccessor.getSessionAttributes().get("username");
        User user = usersService.getUserByUsername(username);
        Chat chat = chatsService.getChat(chatId);

        ChatMessage chatMessage = new ChatMessage(user, chat, chatMessageDTO.getContent());
        chatMessageService.createChatMessage(chatMessage);
        return chatMessage;
    }

    @MessageMapping("/chat.addUser")
    public void addUser(@Payload AddUserDTO addUserDTO,
                        SimpMessageHeaderAccessor headerAccessor) {
        System.out.println("SENDER" + addUserDTO.getSender());
        headerAccessor.getSessionAttributes().put("username", addUserDTO.getSender());
    }
}