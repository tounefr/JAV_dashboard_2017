import { Component, OnInit } from '@angular/core';
import { RequesterService } from '../requester.service'
import { AService } from '../AService'

@Component({
  selector: 'app-facebook',
  templateUrl: './facebook.component.html',
  styleUrls: ['./facebook.component.css']
})
export class FacebookComponent extends AService implements OnInit {

  constructor() {
    super('Facebook', 'http://localhost:8080/connect/facebook')
  }

  ngOnInit() {
  }

  refresh(): void {

  }
}
