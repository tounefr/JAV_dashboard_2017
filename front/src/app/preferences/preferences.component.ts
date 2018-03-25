import { Component, OnInit } from '@angular/core';
import { DashboardComponent } from '../dashboard/dashboard.component'
import { AService } from '../AService'
import {ModuleService} from "../module.service";
import {RequesterService} from "../requester.service";
import {Router} from '@angular/router'
import {Preference} from "./preference";

@Component({
  selector: 'app-preferences',
  templateUrl: './preferences.component.html',
  styleUrls: ['./preferences.component.css'],
  providers: [ModuleService, RequesterService]
})
export class PreferencesComponent implements OnInit {

  preferences: Preference[];

  constructor(
    private requester: RequesterService,
    private moduleService: ModuleService,
    private router: Router
  ) {
    this.preferences = [
      new Preference('CoinMarket'),
      new Preference('Twitter'),
      new Preference('Facebook')
    ]
  }

  ngOnInit() {
    this.requester.checkConnected().subscribe(res => {
      if (res === null)
        this.router.navigate(['/account']);
    })

    this.requester.getUserModules('test').subscribe(res => {
      res.payload.forEach(elem => {
//        this.preferences.push(elem)
      })
    })

  }

  toggleSubscribed(event): void {
    console.log(event.target)
  }




}
