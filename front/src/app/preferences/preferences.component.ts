import { Component, OnInit } from '@angular/core';
import { DashboardComponent } from '../dashboard/dashboard.component'
import { AService } from '../AService'
import {ModuleService} from "../module.service";

@Component({
  selector: 'app-preferences',
  templateUrl: './preferences.component.html',
  styleUrls: ['./preferences.component.css'],
  providers: [ModuleService]
})
export class PreferencesComponent implements OnInit {

  constructor(private moduleService: ModuleService) {

  }

  ngOnInit() {

  }


}
