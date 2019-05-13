import { PeopleService } from './../people.service';
import { ActivatedRoute, Router } from '@angular/router';
import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-people-detail',
  templateUrl: './people-detail.component.html',
  styleUrls: ['./people-detail.component.css']
})
export class PeopleDetailComponent implements OnInit {

  constructor(private _activatedRoute: ActivatedRoute,
    private _peopleService: PeopleService,
    private _router: Router) { }

  person = {}
  movies: any[] = [];

  ngOnInit() {
    this._activatedRoute.params.subscribe(params => {
      let id = params['id'];

      this._peopleService.getById(id)
        .subscribe(response => {
          this.person = response;

          this._peopleService.getParticipatingMovie(this.person).subscribe(
            response => {
              this.movies = response;
            })

        })
    });
  }

  editPerson(person) {
    this._router.navigate(['people/details', person.id, 'edit'])
  }

}
