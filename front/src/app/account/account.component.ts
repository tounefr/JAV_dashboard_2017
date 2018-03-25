import { Component, OnInit, TemplateRef, Input } from '@angular/core';
import { RequesterService } from '../requester.service'
import { AlertService } from "../alert.service";
import {  LoginResponse } from './LoginResponse'
import {AppComponent} from "../app.component";
import { DomSanitizer } from '@angular/platform-browser';

@Component({
  selector: 'app-account',
  templateUrl: './account.component.html',
  styleUrls: ['./account.component.css'],
  providers: [AlertService,AppComponent]
})
export class AccountComponent implements OnInit {

  private username;
  private password;
  alerts: any = [];

  constructor(
    private requester: RequesterService,
    private alertService: AlertService,
    private app: AppComponent,
    private sanitizer: DomSanitizer
  ) {

  }

  ngOnInit() {
  }

  alert(type_: string, msg_: string): void {
    this.alerts.push({type: type_, msg: msg_, timeout: 5000})
  }

  signIn(): void {
    this.alertService.alert('test', 'success')
    this.requester.loginRequest(this.username, this.password)
      .subscribe(res => {
        this.alert('success', 'You\'re connected!')
      }, error => {
        this.alert('danger', 'Wrong username or password')
      })
  }

  signUp(): void {
    console.log('signUp ' + this.username + ' ' + this.password)
    this.requester.registerRequest(this.username, this.password)
      .subscribe(res => {
        console.log('ok');
        this.alertService.alert('Votre compte a bien été créer!', 'success')
      })
  }

}
