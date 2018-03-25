import { Component, Input } from '@angular/core';

export class AService {

  serviceName: string;
  serviceActivated: boolean;
  serviceConnected: boolean;
  serviceConnectUrl: string;
  display: boolean;

  constructor(
    serviceName: string,
    serviceConnectUrl: string = '') {
    this.serviceName = serviceName;
    this.serviceActivated = false;
    this.serviceConnected = false;
    this.display = true;
    this.serviceConnectUrl = serviceConnectUrl;
  }

  toString(): string {
    return this.serviceName;
  }
}
