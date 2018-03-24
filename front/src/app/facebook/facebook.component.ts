import { Component, OnInit } from '@angular/core';
import { RequesterService } from '../requester.service'
import { AService } from '../AService'
import { Observable } from 'rxjs/Observable';
import { Profile } from './profile'

@Component({
  selector: 'app-facebook',
  templateUrl: './facebook.component.html',
  styleUrls: ['./facebook.component.css']
})
export class FacebookComponent extends AService implements OnInit {

  profile: Observable<Profile>;

  constructor(private requester: RequesterService) {
    super('Facebook', 'http://dashboard.epitech.eu:8080/connect/facebook')
    this.profile = null
  }

  ngOnInit() {
    this.refresh()
  }

  checkConnected(): void {
    this.requester.facebookCheckConnected()
      .subscribe(isConnected => this.serviceConnected = Boolean(isConnected))
  }

  refresh(): void {
    this.checkConnected()
    this.getProfile()
  }

  getProfile(): void {
    this.profile = this.requester.getFacebookProfile()
  }
}
