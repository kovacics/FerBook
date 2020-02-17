import { observable } from 'mobx';

export class Chat {
  @observable id;
  @observable name;
  @observable chatMessages;
  @observable createdAt;

  constructor(data) {
    this.id = data.id;
    this.name = data.name;
    this.chatMessages = data.chatMessages || [];
    this.createdAt = data.createdAt;
  }

  addMessage(message) {
    this.chatMessages.push(message);
  }
}
