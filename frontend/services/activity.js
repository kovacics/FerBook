import { request } from './api';
import { Activity } from '../stores/models/activity';

export async function fetchActivities() {
  return request('activities', 'GET');
}

export async function createActivity(appStore, context, imageIDs) {
  let activity = new Activity({ context, user: appStore.currentUser, imageIDs });
  const response = await request('activities', 'POST', activity.serialize(), appStore.authenticationToken);
  return new Activity(response);
}

export async function fetchActivity(id) {
  return request(`activities/${id}`, 'GET');
}

export async function updateActivity(activity, context) {
  await request(`activities/${id}`, 'PUT', { context });
  activity.context = context;
}

export async function deleteActivity(id) {
  return request(`activities/${id}`, 'DELETE');
}

export async function fetchActivitiesByUser(userId) {
  return request(`activities/user/${userId}`, 'GET');
}

export async function fetchActivitiesByUserFriends(userId) {
  return request(`activities/user/${userId}/friends`, 'GET');
}
