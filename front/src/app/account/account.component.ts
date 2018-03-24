import { Component, OnInit, TemplateRef, Input } from '@angular/core';
import { RequesterService } from '../requester.service'

@Component({
  selector: 'app-account',
  templateUrl: './account.component.html',
  styleUrls: ['./account.component.css']
})
export class AccountComponent implements OnInit {

  private username;
  private password;

  constructor(private requester: RequesterService) {}

  ngOnInit() {
  }

  signIn(): void {
    this.requester.loginRequest(this.username, this.password)
      .subscribe(res => {

      })
  }

  signUp(): void {
    console.log('signUp')
  }

}
