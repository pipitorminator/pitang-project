import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import { MovieService } from '../movie.service';
import { GenresService } from 'src/app/services/genres.service';

@Component({
  selector: 'app-movie-edit',
  templateUrl: './movie-edit.component.html',
  styleUrls: ['./movie-edit.component.css']
})
export class MovieEditComponent implements OnInit {

  constructor(private _activatedRoute: ActivatedRoute,
    private _movieService: MovieService,
    private _genreService: GenresService,
    private _router: Router) { }

  movie = {}
  genres: any[] = [];

  ngOnInit() {
    this._activatedRoute.params.subscribe(params => {
      let id = params['id'];

      this._movieService.getById(id)
        .subscribe(response => {
          this.movie = response;
        })
    });


    this._genreService.getDiscover().subscribe(
      response => {
        this.genres = response;
      }
    )

  }

  voltar(movie) {
    this._router.navigate(['movies/details', movie.id])
  }

  salvar(movie, title, lancamento, sinopse, lingua, duracao) {
    movie.title = title;
    movie.release_date = lancamento;
    movie.overview = sinopse;
    movie.original_language = lingua;
    movie.runtime = duracao;
    this._movieService.movieUpdate(movie.id, movie).subscribe();
    this.voltar(movie);

  }

  deleteMovie(id){
    this._movieService.movieDelete(id).subscribe();
    this._router.navigate(['movies/']);
  }

  removeGenre(movie, index) {
    movie.genres.splice(index, 1)
  }

  addGenre(movie, genero) {
    let exist = false;
    movie.genres.forEach(genre => {
      if(genre.id == genero.id){
        exist = true;
      }    
    });
    if (!exist) {
      movie.genres.push(genero)
    }
  }

  removeCast(movie, index) {
    movie.cast.splice(index, 1)

  }

}
