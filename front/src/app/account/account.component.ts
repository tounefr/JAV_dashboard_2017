import { Component, OnInit, TemplateRef, Input } from '@angular/core';
import { RequesterService } from '../requester.service'
import { AlertService } from "../alert.service";
import {  LoginResponse } from './LoginResponse'
import {AppComponent} from "../app.component";
import { DomSanitizer } from '@angular/platform-browser';
import {AccountService} from "./account.service";
import {Router} from '@angular/router'

@Component({
  selector: 'app-account',
  templateUrl: './account.component.html',
  styleUrls: ['./account.component.css'],
  providers: [AlertService,AppComponent,AccountService]
})
export class AccountComponent implements OnInit {

  private username;
  private password;
  alerts: any = [];
  connected: boolean;

  constructor(
    private requester: RequesterService,
    private app: AppComponent,
    private sanitizer: DomSanitizer,
    private userService: AccountService,
    private router: Router
  ) {
    this.connected = false;
  }

  ngOnInit() {
    this.requester.checkConnected().subscribe(res => {
      console.log(res)
      this.connected = (res != null)
    })
  }

  alert(type_: string, msg_: string): void {
    this.alerts.push({type: type_, msg: msg_, timeout: 5000})
  }

  signIn(): void {
    this.requester.loginRequest(this.username, this.password)
      .subscribe(
        success => {
          this.alert('success', 'You\'re connected!')
          this.userService.toggleConnected()
          localStorage.setItem('username', this.username)
          this.router.navigate(['/']);
      },
        error => {
          this.alert('danger', error.error.message)
      })
  }

  signUp(): void {
    console.log('signUp ' + this.username + ' ' + this.password)
    this.requester.registerRequest(this.username, this.password)
      .subscribe(success => {
        this.alert('success', 'Your account has been successfully created!')
        this.signIn()
      }, error => {
        console.log(error)
        this.alert('danger', 'Failed to create your account: ' + error.error.message)
      })
  }

  disconnect(): void {
    this.requester.disconnect().subscribe(res => {
      this.connected = false;
    })
  }

}
