import { PeopleService } from './../people.service';
import { ActivatedRoute, Router } from '@angular/router';
import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-people-edit',
  templateUrl: './people-edit.component.html',
  styleUrls: ['./people-edit.component.css']
})
export class PeopleEditComponent implements OnInit {

  constructor(private _activatedRoute: ActivatedRoute,
    private _personService: PeopleService,
    private _router: Router) { }

  person = {}
  movies: any[] = [];

  ngOnInit() {
    this._activatedRoute.params.subscribe(params => {
      let id = params['id'];

      this._personService.getById(id)
        .subscribe(response => {
          this.person = response;

          this._personService.getParticipatingMovie(this.person).subscribe(
            response => {
              this.movies = response;
            }
          )

        })

    });
    

  }

  voltar(person) {
    this._router.navigate(['people/details', person.id])
  }

  salvar(person, name, genero, local_nascimento, pais_atual, altura) {
    person.name = name;
    person.gender = genero;
    person.place_of_birth = local_nascimento;
    person.actual_country = pais_atual;
    person.height = altura;
    this._personService.personUpdate(person.id, person).subscribe();
    this.voltar(person);

  }

}
