import { Component } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'Pitang DB';

  collapsed = true;
  account: Account;

  constructor(private router: Router) { }

  ngOnInit() {

  }

  toggleCollapsed(): void {
    this.collapsed = !this.collapsed;
  }

  search(choice,input){
    
  }

}
