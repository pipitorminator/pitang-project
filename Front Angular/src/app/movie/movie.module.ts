
import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { MovieComponent } from './movie.component';
import { MovieDetailComponent } from './movie-detail/movie-detail.component';
import { MovieRoutingModule } from './movie.routing.module';
import { MovieEditComponent } from './movie-edit/movie-edit.component';
import { MovieService } from './movie.service';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';


@NgModule({
  declarations: [
    MovieComponent,
    MovieDetailComponent,
    MovieEditComponent
  ],
  imports: [
    CommonModule,
    MovieRoutingModule,
    NgbModule
  ],
  providers:[
    MovieService
  ],
  exports: []
})
export class MovieModule { }
