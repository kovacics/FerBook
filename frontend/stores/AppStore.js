import { observable, computed } from "mobx";
import decode from 'jwt-decode';

export class AppStore {
  @observable
  currentUser;

  authenticationToken;

  @computed
  get isLoggedIn() {
    return Boolean(this.currentUser);
  }

  @computed
  get authenticationTokenClaims() {
    return decode(this.authenticationToken);
  }

  reset() {
    this.currentUser = null;
    this.authenticationToken = null;
  }
}
