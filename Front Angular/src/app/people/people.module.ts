import { PeopleService } from './people.service';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { PeopleComponent } from './people.component';
import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { PeopleRoutingModule } from './people.routing.module';
import { PeopleDetailComponent } from './people-detail/people-detail.component';
import { PeopleEditComponent } from './people-edit/people-edit.component';

@NgModule({
  declarations: [
    PeopleComponent,
    PeopleDetailComponent,
    PeopleEditComponent,
  ],
  imports: [
    CommonModule,
    PeopleRoutingModule,
    NgbModule
  ],
  providers: [
    PeopleService
  ]
})
export class PeopleModule { }
