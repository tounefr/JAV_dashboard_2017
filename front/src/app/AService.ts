import { Input } from '@angular/core';

export class AService {

  serviceName: string;
  serviceActivated: boolean;
  serviceConnected: boolean;
  serviceConnectUrl: string;

  constructor(serviceName: string, serviceConnectUrl: string = '') {
    this.serviceName = serviceName;
    this.serviceActivated = true;
    this.serviceConnected = false;
    this.serviceConnectUrl = serviceConnectUrl;
  }

  toString(): string {
    return this.serviceName;
  }
}
