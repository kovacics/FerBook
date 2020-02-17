import decode from 'jwt-decode';

import { request } from './api';
import { User } from '../stores/models/user';

export async function fetchUser(id) {
  const response = await request(`users/${id}`, 'GET');
  return new User(response);
}

export async function fetchUsersByQuery(query) {
  return request(`users/start/${query}`, 'GET');
}

export async function register(payload) {
  return request('register', 'POST', payload);
}

export async function login(appStore, payload) {
  const response = await request('login', 'POST', payload);
  const claims = decode(response);
  
  appStore.currentUser = await fetchUser(claims.id);
  appStore.authenticationToken = response;
}

export async function logout(appStore) {
  appStore.reset();
}

export async function confirmUser(token) {
  return request(`users/confirmation/${token}`, 'GET');
}

export async function updateUser(appStore, payload) {
  await request(`users/${appStore.currentUser.id}`, 'PATCH', payload, appStore.authenticationToken);
  Object.assign(appStore.currentUser, payload);
}
