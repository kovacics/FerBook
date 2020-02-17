import { request } from "./api";

export async function fetchFriendships() {
  return request('friendships', 'GET');
}

export async function fetchFriendship(id) {
  return request(`friendships/${id}`, 'GET');
}

export async function fetchFriendshipRequestsForUser(userId) {
  return request(`friendships/${userId}/requests`, 'GET');
}

export async function fetchFriendshipsForUser(userId) {
  return request(`friendships/${userId}/friends`, 'GET');
}

export async function confirmFriendship(id) {
  return request(`friendships/${id}/confirm`, 'GET');
}

export async function rejectFriendship(id) {
  return request(`friendships/${id}/reject`, 'GET');
}

export async function createFriendship(appStore, friend) {
  return request('friendships', 'POST', {
    friendId: friend.id,
  }, appStore.authenticationToken);
}

export async function deleteFriendship(id) {
  return request(`friendships/${id}`, 'DELETE');
}