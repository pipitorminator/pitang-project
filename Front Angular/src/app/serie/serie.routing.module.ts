import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { SerieComponent } from './serie.component';
import { SerieDetailComponent } from './serie-detail/serie-detail.component';
import { SerieEditComponent } from './serie-edit/serie-edit.component';

const routes: Routes = [
    {  path: '', component: SerieComponent },
    {  path: 'details/:id', component: SerieDetailComponent },

    { path: 'details/:id/edit', component: SerieEditComponent}
];

@NgModule({
    imports: [RouterModule.forChild(routes)],
    exports: [RouterModule]
})
export class SerieRoutingModule { }