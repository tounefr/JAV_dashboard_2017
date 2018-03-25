
export class Preference {
  name: string;
  subscribed: boolean;
  settings: any;
  component: any;

  constructor(name: string, component: any) {
    this.name = name
    this.subscribed = false;
    this.component = component;
  }
}
