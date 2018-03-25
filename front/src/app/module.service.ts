import { Injectable } from '@angular/core';
import { FacebookComponent } from './facebook/facebook.component'
import { TwitterComponent } from './twitter/twitter.component'
import { CoinmarketcapComponent } from './coinmarketcap/coinmarketcap.component'
import { AService } from './AService'
import {RequesterService} from "./requester.service";

@Injectable()
export class ModuleService {

  services: AService[];

  constructor(
    private requester: RequesterService,
    private twitterComponent: TwitterComponent,
    private facebookComponent: FacebookComponent,
    private coinMarketCap: CoinmarketcapComponent) {
    this.services = [twitterComponent, facebookComponent, coinMarketCap]
    this.loadPrefs()

  }

  allServicesDisabled(): boolean {
    let allServicesDisabled = true;
    this.services.forEach(elem => {
      if (elem.serviceActivated)
        allServicesDisabled = false;
    })
    return allServicesDisabled;
  }

  loadPrefs(): void {
    if (localStorage.getItem('activatedServices')) {
      let servicesStr = JSON.parse(localStorage.getItem('activatedServices'))
      servicesStr.forEach(serviceName => {
        this.services.forEach(service => {
          if (service.serviceName === serviceName)
            service.serviceActivated = true;
        });
      });
    }
    console.log('loadPrefs');
  }

  savePrefs(): void {
    let servicesStr = [];
    this.services.forEach(service => {
      if (service.serviceActivated) {
        servicesStr.push(service.serviceName);
      }
    });
    localStorage.setItem('activatedServices', JSON.stringify(servicesStr));
    console.log('savePrefs')
  }

}
