import { PeopleService } from './people.service';
import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-people',
  templateUrl: './people.component.html',
  styleUrls: ['./people.component.css']
})
export class PeopleComponent implements OnInit {

  constructor(private _peopleService: PeopleService,
    private _router: Router) { }

  page = 1;
  totalelements = Number;

  people = [];

  ngOnInit() {

    this._peopleService.getDiscover(this.page).subscribe(
      response => {
        this.people = response['content'];
        this.totalelements= response['totalElements']
      }
    )
  }

  changePage() {
    this._peopleService.getDiscover(this.page).subscribe(
      response => {
        this.people = response['content'];
      }
    )
  }

  goEdit(person){
    this._router.navigate(['people/details', person.id]);
  }

}
