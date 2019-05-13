import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { GenresService } from './../../services/genres.service';
import { SerieService } from '../serie.service';

@Component({
  selector: 'app-serie-edit',
  templateUrl: './serie-edit.component.html',
  styleUrls: ['./serie-edit.component.css']
})
export class SerieEditComponent implements OnInit {

  constructor(private _activatedRoute: ActivatedRoute,
    private _serieService: SerieService,
    private _genreService: GenresService,
    private _router: Router) { }

  serie = {}
  genres: any[] = [];

  ngOnInit() {
    this._activatedRoute.params.subscribe(params => {
      let id = params['id'];

      this._serieService.getById(id)
        .subscribe(response => {
          this.serie = response;
        })
    });


    this._genreService.getDiscover().subscribe(
      response => {
        this.genres = response;
      }
    )

  }

  voltar(serie) {
    this._router.navigate(['tv/details', serie.id])
  }

  salvar(serie, name, lancamento, sinopse, lingua,temporadas, duracao) {
    serie.original_name = name;
    serie.first_air_date = lancamento;
    serie.overview = sinopse;
    serie.original_language = lingua;
    serie.number_of_seasons = temporadas;
    serie.episode_runtime = duracao;
    this._serieService.serieUpdate(serie.id, serie).subscribe();
    this.voltar(serie);

  }

  deleteSerie(id){
    this._serieService.serieDelete(id).subscribe();
    this._router.navigate(['tv/']);
  }

  removeGenre(serie, index) {
    serie.genres.splice(index, 1)
  }

  addGenre(serie, genero) {
    let exist = false;
    serie.genres.forEach(genre => {
      if(genre.id == genero.id){
        exist = true;
      }    
    });
    if (!exist) {
      serie.genres.push(genero)
    }
  }

  removeCast(serie, index) {
    serie.cast.splice(index, 1)

  }

}
