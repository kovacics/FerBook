import { request } from './api';
import { Reaction } from '../stores/models/reaction';

export async function createReaction(appStore, activity, reactionText) {
  const reaction = new Reaction({ user: appStore.currentUser, activity, reaction: reactionText, reactionText });
  const response = await request('reactions', 'POST', reaction.serialize(), appStore.authenticationToken);
  return new Reaction(response);
}

export async function deleteReaction(appStore, id) {
  return request(`reactions/${id}`, 'DELETE', undefined, appStore.authenticationToken);
}
