import { PeopleEditComponent } from './people-edit/people-edit.component';
import { PeopleDetailComponent } from './people-detail/people-detail.component';
import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { PeopleComponent } from './people.component';

const routes: Routes = [
    {  path: '', component: PeopleComponent },
    {  path: 'details/:id', component: PeopleDetailComponent },

    { path: 'details/:id/edit', component: PeopleEditComponent}
];

@NgModule({
    imports: [RouterModule.forChild(routes)],
    exports: [RouterModule]
})
export class PeopleRoutingModule { }