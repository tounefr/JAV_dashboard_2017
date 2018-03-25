import { Component, OnInit } from '@angular/core';
import { PreferencesComponent } from '../preferences/preferences.component'
import {ModuleService} from "../module.service";

@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.css'],
  providers: [ModuleService]
})
export class DashboardComponent implements OnInit {

  constructor(module: ModuleService) {

  }

  ngOnInit() {

  }


}
