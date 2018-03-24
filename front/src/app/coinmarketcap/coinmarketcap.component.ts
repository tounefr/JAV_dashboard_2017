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

  constructor(private requesterService: RequesterService) {
    super('Coin Market Cap')
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
      this.requesterService.getCurrency(currency.name)
        .subscribe(currencyValue => {
          currency.value = Number(currencyValue)
        })
    })
  }

  refresh(): void {
    this.getCurrencies()
  }

}
