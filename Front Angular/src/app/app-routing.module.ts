import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';

const routes: Routes = [
  { path: 'movies', loadChildren: './movie/movie.module#MovieModule' },
  { path: 'tv', loadChildren: './serie/serie.module#SerieModule' },
  { path: 'people', loadChildren: './people/people.module#PeopleModule' },
  { path: '', pathMatch: 'full', redirectTo: 'movies' },
  { path: '**', redirectTo: 'movies' }
];

@NgModule({
  imports: [RouterModule.forRoot(routes, { useHash: true })],
  exports: [RouterModule]
})
export class AppRoutingModule { }
