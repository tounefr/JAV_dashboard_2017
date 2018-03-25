import { Component, SecurityContext, Injectable } from '@angular/core';
import { DomSanitizer } from '@angular/platform-browser';

@Injectable()
export class AlertService {
  alerts: any = [];

  constructor(sanitizer: DomSanitizer) {
    this.alerts = this.alerts.map((alert: any) => ({
      type: alert.type,
      msg: sanitizer.sanitize(SecurityContext.HTML, alert.msg)
    }));

  }

  alert(msg_: string, type_: string): void {
    this.alerts.push({type: type_, msg: msg_})
  }
}
