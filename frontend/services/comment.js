import { Comment } from '../stores/models/comment';
import { request } from './api';

export async function createComment(appStore, activity, content) {
  const comment = new Comment({ activity, content });
  const response = await request('comments', 'POST', comment.serialize(), appStore.authenticationToken);
  return new Comment(response);
}

export async function deleteComment(id) {
  return request(`comments/${id}`, 'DELETE');
}
