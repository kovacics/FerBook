import { User } from './user';

export class Friendship {
  constructor(data) {
    this.id = data.id;

    if (data.user instanceof User) {
      this.user = data.user;
    } else if (data.user) {
      this.user = new User(data.user);
    }

    if (data.friend instanceof User) {
      this.friend = data.friend;
    } else if (data.friend) {
      this.friend = new User(data.friend);
    }
  }

  serialize() {
    return {
      id: this.id,
      user: {
        id: this.user.id,
      },
      friend: this.friend && {
        id: this.friend.id,
      },
    }
  }
}