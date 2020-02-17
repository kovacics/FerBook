import { request } from './api';
import { Chat } from '../stores/models/chat';

export async function fetchChats() {
  const chats = await request('chats', 'GET');
  return chats.map((chat) => new Chat(chat));
}

export async function fetchChatsForUser(id) {
  const chats = await request(`chats/user/${id}`, 'GET');
  return chats.map((chat) => new Chat(chat));
}

export async function fetchChat(id) {
  return request(`chats/${id}`, 'GET');
}

export async function deleteChat(id) {
  return request(`chats/${id}`, 'DELETE');
}

export async function createChat(appStore, name, userIds) {
  return request('chats', 'POST', {
    name,
    userIds: [
      appStore.currentUser.id,
      ...userIds,
    ],
  });
}
