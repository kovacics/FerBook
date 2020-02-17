import { BASE_URL } from "../../services/api";
import { computed, observable } from "mobx";

export class User {
  @observable firstName;
  @observable lastName;

  constructor(data) {
    this.id = data.id;
    this.email = data.email;
    this.username = data.username;
    this.admin = data.admin;
    this.firstName = data.firstName;
    this.lastName = data.lastName;
    this.pictureUrl = data.pictureUrl;
  }

  @computed
  get fullName() {
    return `${this.firstName} ${this.lastName}`;
  }

  get profilePictureUrl() {
    return this.pictureUrl || '/default-profile-picture.png';
  }

  equals(otherUser) {
    return otherUser && this.id === otherUser.id;
  }
}
