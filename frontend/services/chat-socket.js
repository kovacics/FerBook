import { bind } from 'decko';
import * as SockJs from 'sockjs-client';
import * as Stomp from 'stompjs';
import { BASE_URL } from './api';

export class ChatSocket {
  user;
  chatId;
  onMessage;

  socket;
  stompClient;

  constructor(user, chatId, onMessage) {
    this.user = user;
    this.chatId = chatId;
    this.onMessage = onMessage;
    
    this.createClient();
    this.connect();
  }

  createClient() {
    this.socket = new SockJs(`${BASE_URL}ws`);
    this.stompClient = Stomp.over(this.socket);
  }

  connect() {
    this.stompClient.connect({ userId: this.user.id }, this.handleConnect);
  }

  @bind
  handleConnect() {
    this.stompClient.subscribe(`/topic/public/${this.chatId}`, this.handleReceiveMessage);
    this.stompClient.send('/app/chat.addUser', {}, JSON.stringify({ sender: this.user.username }));
  }

  @bind
  sendMessage(content) {
    if (this.stompClient) {
      this.stompClient.send(`/app/chat.sendMessage/${this.chatId}`, {}, JSON.stringify({ content }));
    }
  }

  @bind
  handleReceiveMessage(payload) {
    const message = JSON.parse(payload.body);
    this.onMessage(message);
  }
}
