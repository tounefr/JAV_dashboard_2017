import { Injectable } from '@angular/core';
import { User } from './user'

@Injectable()
export class AccountService {

  user: User;

  constructor() {
    let user = new User()
    if (localStorage.getItem('user'))
      user = JSON.parse(localStorage.getItem('user'))
    this.user = user
  }

  save(): void {
    localStorage.setItem('user', JSON.stringify(this.user))
  }

  toggleConnected(): void {
    this.user.connected = !this.user.connected
    this.save()
  }

  isConnected(): boolean {
    return this.user.connected
  }
}
