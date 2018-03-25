import { Component, OnInit } from '@angular/core';
import { RequesterService } from '../requester.service'
import {AService} from "../AService";
import { Currency } from './currency'

@Component({
  selector: 'app-coinmarketcap',
  templateUrl: './coinmarketcap.component.html',
  styleUrls: ['./coinmarketcap.component.css']
})
export class CoinmarketcapComponent extends AService implements OnInit {

  currencies: Currency[];

  constructor(private requester: RequesterService) {
    super('CoinMarket')
    this.currencies = [
      new Currency('bitcoin'),
      new Currency('zcash'),
      new Currency('ethereum'),
      new Currency('ripple'),
      new Currency('litecoin')
    ];
  }

  ngOnInit() {
    this.refresh()
  }

  getCurrencies(): void {
    this.currencies.forEach(currency => {
      this.requester.getCurrency(currency.name)
        .subscribe(currencyValue => {
          currency.value = Number(currencyValue)
        })
    })
  }

  unsubscribe(): void {
    this.requester.unsubscribeModule(this.serviceName).subscribe(res => {
      this.display = false;
    })
  }

  refresh(): void {
    this.getCurrencies()
  }

}
