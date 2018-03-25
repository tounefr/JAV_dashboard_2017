
import {AlertService} from "./alert.service";
import { Component } from '@angular/core';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css'],
  providers: [AlertService]
})
export class AppComponent {
  title = 'app';

  constructor(private alertService: AlertService) {

  }

}
