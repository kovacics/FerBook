import { observable, computed } from 'mobx';

import { User } from './user';
import { Reaction } from './reaction';
import { Comment } from './comment';

export class Activity {
  @observable context;
  @observable reactions;
  @observable comments;

  constructor(data) {
    this.id = data.id;
    this.context = data.context;
    this.reactions = data.reactions ? data.reactions.map((reaction) => new Reaction(reaction)) : [];
    this.comments = data.comments ? data.comments.map((comment) => new Comment(comment)) : [];
    this.imageIDs = data.imageIDs;
    this.pictures = data.pictures || [];

    if (data.user instanceof User) {
      this.user = data.user;
    } else if (data.user) {
      this.user = new User(data.user);
    }

    if (data.receiver instanceof User) {
      this.receiver = data.receiver;
    } else if (data.receiver) {
      this.receiver = new User(data.receiver);
    }
  }

  reactionsFor(reactionText) {
    return this.reactions.filter((reaction) => reaction.reactionText === reactionText);
  }

  @computed
  get commentsCount() {
    return this.comments.length;
  }

  serialize() {
    return {
      id: this.id,
      userID: this.user.id,
      receiver: this.receiver && this.receiver.id,
      context: this.context,
      imageIDs: this.imageIDs,
    }
  }
}