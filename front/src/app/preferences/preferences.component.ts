import { Component, OnInit } from '@angular/core';
import { DashboardComponent } from '../dashboard/dashboard.component'
import { AService } from '../AService'

@Component({
  selector: 'app-preferences',
  templateUrl: './preferences.component.html',
  styleUrls: ['./preferences.component.css'],
  providers: [DashboardComponent]
})
export class PreferencesComponent implements OnInit {

  services: AService[];

  constructor(private dashboardComponent: DashboardComponent) {
    this.services = dashboardComponent.services;
  }

  ngOnInit() {
    this.loadPrefs()
  }

  loadPrefs(): void {
    if (localStorage.getItem('activatedServices')) {
      let servicesStr = JSON.parse(localStorage.getItem('activatedServices'))
      servicesStr.forEach(serviceName => {
        this.dashboardComponent.services.forEach(service => {
          if (service.serviceName === serviceName)
            service.serviceActivated = true;
        });
      });
    }
    console.log('loadPrefs');
  }
  savePrefs(): void {
    let servicesStr = [];
    this.dashboardComponent.services.forEach(service => {
      if (service.serviceActivated) {
        servicesStr.push(service.serviceName);
      }
    });
    localStorage.setItem('activatedServices', JSON.stringify(servicesStr));
    console.log('savePrefs')
  }

}
