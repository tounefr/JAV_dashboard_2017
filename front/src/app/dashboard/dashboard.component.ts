import { Component, OnInit, Input } from '@angular/core';
import { PreferencesComponent } from '../preferences/preferences.component'
import {ModuleService} from "../module.service";
import {RequesterService} from "../requester.service";
import {Router} from '@angular/router'
import {Preference} from '../preferences/preference'
import {CoinmarketcapComponent} from "../coinmarketcap/coinmarketcap.component";
import {TwitterComponent} from "../twitter/twitter.component";
import {FacebookComponent} from "../facebook/facebook.component";

@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.css'],
  providers: [ModuleService,CoinmarketcapComponent]
})
export class DashboardComponent implements OnInit {

  @Input moduleRegister;
  alerts: any = [];
  preferences: Preference[];

  constructor(
    private requester: RequesterService,
    private module: ModuleService,
    private router: Router,
    private coinMarketCap: CoinmarketcapComponent,
    private twitter: TwitterComponent,
    private facebook: FacebookComponent
  ) {
    this.preferences = [
      new Preference('CoinMarket', coinMarketCap),
      new Preference('Twitter', twitter),
      new Preference('Facebook', facebook)
    ]
  }

  ngOnInit() {
    this.requester.checkConnected().subscribe(res => {
      if (res === null)
        this.router.navigate(['/account']);
    })
    this.requester.getUserModules('test').subscribe(res => {
      res.payload.forEach(module => {
        let preference = this.preferences.find(elem => elem.name === module.name)
        console.log(preference)
        preference.component.serviceActivated = true;
      })
    })
  }

  isServiceActivated(name: string) {
    let preference = this.preferences.find(elem => elem.name === name)
    return preference.component.serviceActivated
  }

  registerModule(moduleRegister): void {
    let elem = this.preferences.find(elem => elem.name === moduleRegister.value)
    this.requester.subscribeModule(moduleRegister.value).subscribe(res => {
      elem.subscribed = true;
      elem.component.serviceActivated = true;
      this.alerts.push({type: 'success', msg: res.payload})
    },
    error => {
      this.alerts.push({type: 'danger', msg: error.error.message})
    })
  }


}
