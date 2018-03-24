import { Component, OnInit } from '@angular/core';
import { PreferencesComponent } from '../preferences/preferences.component'
import { AService } from '../AService'
import { FacebookComponent } from '../facebook/facebook.component'
import { TwitterComponent } from '../twitter/twitter.component'
import { CoinmarketcapComponent } from '../coinmarketcap/coinmarketcap.component'

@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.css'],
  providers: [TwitterComponent,FacebookComponent,CoinmarketcapComponent]
})
export class DashboardComponent implements OnInit {

  services: AService[];

  constructor(private twitterComponent: TwitterComponent,
              private facebookComponent: FacebookComponent,
              private coinMarketCap: CoinmarketcapComponent) {
    this.services = [twitterComponent, facebookComponent, coinMarketCap]
  }

  ngOnInit() {
  }

  allServicesDisabled(): boolean {
    let allServicesDisabled = true;
    this.services.forEach(elem => {
      if (elem.serviceActivated)
        allServicesDisabled = false;
    })
    return allServicesDisabled;
  }

}
