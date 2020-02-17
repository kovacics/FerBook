import { Activity } from "./activity";
import { User } from "./user";

export class Comment {
  constructor(data) {
    this.id = data.id;
    this.content = data.content;
    this.user = data.user && new User(data.user);
    this.activity = data.activity && new Activity(data.activity);
    this.createdAt = data.createdAt;
  }

  serialize() {
    return {
      id: this.id,
      content: this.content,
      activityID: this.activity.id,
    }
  }
}
