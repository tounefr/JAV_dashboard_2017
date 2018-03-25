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
import {RegisterResponse} from "./account/RegisterResponse";

const httpOptions = {
  headers: new HttpHeaders({
    'Content-Type':  'application/json',
  }),
  withCredentials: true
};

@Injectable()
export class RequesterService {

  endpointUrl = 'http://dashboard.epitech.eu:8080/';

  constructor(private http: HttpClient) { }

  getTwitterStats(): Observable<Stats> {
    return this.http.get<Stats>(this.endpointUrl + 'twitter/stats', httpOptions)
  }

  getTwitterMentions(): Observable<Tweet[]> {
    return this.http.get<Tweet[]>(this.endpointUrl + 'twitter/mentions', httpOptions)
  }

  getTweets(): Observable<Tweet[]> {
    return this.http.get<Tweet[]>(this.endpointUrl + 'twitter/my-tweets', httpOptions)
  }

  getCurrency(currency: string): Observable<string> {
    return this.http.get<string>(this.endpointUrl + 'coinmarket?currency=' + currency, httpOptions)
  }

  getTwitterFollowers(): Observable<Profile[]> {
    return this.http.get<Profile[]>(this.endpointUrl + 'twitter/followers', httpOptions)
  }

  twitterCheckConnected(): Observable<boolean> {
    return this.http.get<boolean>(this.endpointUrl + 'twitter/connected', httpOptions)
  }

  facebookCheckConnected(): Observable<boolean> {
    return this.http.get<boolean>(this.endpointUrl + 'facebook/connected', httpOptions)
  }

  facebookTimeline(): Observable<Post[]> {
    return this.http.get<Post[]>(this.endpointUrl + 'facebook/feed-posts', httpOptions)
  }

  loginRequest(username_: string, password_: string): Observable<LoginResponse>{
    return this.http.post<LoginResponse>(this.endpointUrl + 'login', {username: username_, password: password_}, httpOptions)
  }

  checkConnected(): Observable<string> {
    return this.http.get<string>(this.endpointUrl + 'login', httpOptions)
  }

  getModules(): Observable<string> {
    return this.http.get<string>(this.endpointUrl + 'modules', httpOptions)
  }

  registerRequest(username: string, password: string): Observable<RegisterResponse> {
    return this.http.post<RegisterResponse>(this.endpointUrl + 'users/register', {username: username, password: password}, httpOptions)
  }

  getFacebookProfile(): Observable<FacebookProfile> {
    return this.http.get<FacebookProfile>(this.endpointUrl + 'facebook/profile', httpOptions)
  }

  disconnect(): Observable<string> {
    return this.http.get<string>(this.endpointUrl + 'logout', httpOptions)
  }

  getAvailableModules(): Observable<Object> {
    return this.http.get<Object>(this.endpointUrl + "modules", httpOptions)
  }

  getUserModules(user: string): Observable<Object> {
    return this.http.get<Object>(this.endpointUrl + "users/" + user + "/modules", httpOptions)
  }

  subscribeModule(id: string): Observable<Object> {
    return this.http.get<Object>(this.endpointUrl + "modules/" + id + "/subscribe", httpOptions)
  }

  unsubscribeModule(id: string): Observable<Object> {
    return this.http.get<Object>(this.endpointUrl + "modules/" + id + "/unsubscribe", httpOptions)
  }
}
