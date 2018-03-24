import { Component, OnInit } from '@angular/core';
import { RequesterService } from '../requester.service'
import { Stats } from './stats'
import {AService} from "../AService";
import { Tweet } from './tweet'
import { Profile } from './profile'
import { Observable } from 'rxjs/Observable';
import { delay, share } from 'rxjs/operators';

@Component({
  selector: 'app-twitter',
  templateUrl: './twitter.component.html',
  styleUrls: ['./twitter.component.css']
})
export class TwitterComponent extends AService implements OnInit {

  tweets: Observable<Tweet[]>;
  mentions: Observable<Tweet[]>;
  stats: Observable<Stats>;
  followers: Observable<Profile[]>;

  constructor(private requester: RequesterService) {
    super('Twitter', 'http://localhost:8080/connect/twitter')
    this.tweets = null
    this.stats = null
    this.mentions = null
    this.followers = null
  }

  ngOnInit() {
    this.refresh()
  }

  checkConnected(): void {
    this.requester.twitterCheckConnected()
      .subscribe(isConnected => this.serviceConnected = Boolean(isConnected))
  }

  getTweets(): void {
    this.tweets = this.requester.getTweets()
  }

  getMentions(): void {
    this.mentions = this.requester.getTwitterMentions()
  }

  getTwitterStats(): void {
    this.stats = this.requester.getTwitterStats()
  }

  getFollowers(): void {
    this.followers = this.requester.getTwitterFollowers()
  }

  refresh(): void {
    console.log('refresh')
    this.checkConnected()
    this.getTwitterStats()
    this.getTweets()
    this.getMentions()
    this.getFollowers()
  }
}
