import { User } from './user';
import { Activity } from './activity';

export const reactions = [
  'LIKE',
  'LOVE',
  'HAHA',
  'WOW',
  'SAD',
  'ANGRY',
];

export class Reaction {
  constructor(data) {
    this.id = data.id;
    this.user = data.user && new User(data.user);
    this.reaction = data.reaction;
    this.reactionText = data.reactionText;
    this.activity = data.activity && new Activity(data.activity);
  }

  serialize() {
    return {
      id: this.id,
      userID: this.user.id,
      reaction: this.reaction,
      activityID: this.activity.id,
    }
  }
}