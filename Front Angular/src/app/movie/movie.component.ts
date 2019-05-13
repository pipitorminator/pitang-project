import { Component, Input, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { MovieService } from './movie.service';

@Component({
  selector: 'app-movie',
  templateUrl: './movie.component.html',
  styleUrls: ['./movie.component.css']
})
export class MovieComponent implements OnInit {

  page = 1;
  totalelements = Number;

  constructor(private _movieService: MovieService,
    private _router: Router) { }

  movies = [];

  ngOnInit() {
    this._movieService.getDiscover(this.page).subscribe(
      response => {
        this.movies = response['content'];
        this.totalelements = response['totalElements']
      }
    )
  }

  changePage() {
    this._movieService.getDiscover(this.page).subscribe(
      response => {
        this.movies = response['content'];
      }
    )

  }

  goEdit(movie) {
    this._router.navigate(['movies/details', movie.id])
  }

}
