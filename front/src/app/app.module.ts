import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppComponent } from './app.component';
import { AppRoutingModule } from './app-routing.module';
import { DashboardComponent } from './dashboard/dashboard.component';
import { AccountComponent } from './account/account.component';
import { TwitterComponent } from './twitter/twitter.component';
import { FacebookComponent } from './facebook/facebook.component';
import { CoinmarketcapComponent } from './coinmarketcap/coinmarketcap.component';
import { PreferencesComponent } from './preferences/preferences.component';
import { RequesterService } from './requester.service'
import { HttpClientModule }    from '@angular/common/http';
import { FormsModule } from '@angular/forms'
import { AlertModule } from 'ngx-bootstrap/alert';
import {AccountService} from "./account/account.service";

@NgModule({
  declarations: [
    AppComponent,
    DashboardComponent,
    AccountComponent,
    TwitterComponent,
    FacebookComponent,
    CoinmarketcapComponent,
    PreferencesComponent
  ],
  imports: [
    FormsModule,
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    AlertModule.forRoot()
  ],
  providers: [
    RequesterService,
    TwitterComponent,
    FacebookComponent,
    CoinmarketcapComponent,
    PreferencesComponent,
    AccountService
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
