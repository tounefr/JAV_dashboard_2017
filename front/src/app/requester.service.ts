import { Injectable } from '@angular/core';

import { HttpClient, HttpHeaders } from '@angular/common/http';

import { Observable } from 'rxjs/Observable';
import { of } from 'rxjs/observable/of';
import { catchError, map, tap } from 'rxjs/operators';
import { Stats } from './twitter/stats'
import { Tweet } from './twitter/tweet'
import { Profile } from './twitter/profile'
import {Profile as FacebookProfile} from './facebook/profile'
import {LoginResponse} from './account/LoginResponse'

import { Currency } from './coinmarketcap/currency'
import {Post} from "./facebook/post";

const httpOptions = {
  headers: new HttpHeaders({
    'Content-Type':  'application/json'
  })
};

@Injectable()
export class RequesterService {

  endpointUrl = 'http://dashboard.epitech.eu:8080/';

  constructor(private http: HttpClient) { }

  getTwitterStats(): Observable<Stats> {
    return this.http.get<Stats>(this.endpointUrl + 'twitter/stats')
  }

  getTwitterMentions(): Observable<Tweet[]> {
    return this.http.get<Tweet[]>(this.endpointUrl + 'twitter/mentions')
  }

  getTweets(): Observable<Tweet[]> {
    return this.http.get<Tweet[]>(this.endpointUrl + 'twitter/my-tweets')
  }

  getCurrency(currency: string): Observable<string> {
    return this.http.get<string>(this.endpointUrl + 'coinmarket?currency=' + currency)
  }

  getTwitterFollowers(): Observable<Profile[]> {
    return this.http.get<Profile[]>(this.endpointUrl + 'twitter/followers')
  }

  twitterCheckConnected(): Observable<boolean> {
    return this.http.get<boolean>(this.endpointUrl + 'twitter/connected')
  }

  facebookCheckConnected(): Observable<boolean> {
    return this.http.get<boolean>(this.endpointUrl + 'facebook/connected')
  }

  facebookTimeline(): Observable<Post[]> {
    return this.http.get<Post[]>(this.endpointUrl + 'facebook/feed-posts')
  }

  loginRequest(username: string, password: string): Observable<LoginResponse>{
    return this.http.post<LoginResponse>(this.endpointUrl + 'login', {username: username, password: password})
  }

  registerRequest(username: string, password: string): Observable<string> {
    return this.http.post<string>(this.endpointUrl + 'users/register', {username: username, password: password})
  }

  getFacebookProfile(): Observable<FacebookProfile> {
    return this.http.get<FacebookProfile>(this.endpointUrl + 'facebook/profile')
  }
}
