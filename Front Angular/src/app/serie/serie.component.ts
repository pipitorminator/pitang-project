import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { SerieService } from './serie.service';

@Component({
  selector: 'app-serie',
  templateUrl: './serie.component.html',
  styleUrls: ['./serie.component.css']
})
export class SerieComponent implements OnInit {
  constructor(private _serieService: SerieService,
    private _router: Router) { }

    page = 1;
    totalelements = Number;

  series = [];

  ngOnInit() {
    this._serieService.getDiscover(this.page).subscribe(
      response => {
        this.series = response['content'];
        this.totalelements = response['totalElements']
      }
    )
  }

  changePage() {
    this._serieService.getDiscover(this.page).subscribe(
      response => {
        this.series = response['content'];
      }
    )

  }

  goEdit(serie) {
    this._router.navigate(['tv/details', serie.id])
  }

}
