import { HttpClient } from '@angular/common/http';
import { BaseService } from './../services/base/base.service';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class PeopleService extends BaseService {

  constructor(httpClient: HttpClient) {
    super(httpClient);
  }

  getDiscover(page){
    return this._httpClient.get(`${this.URL}people/?size=20&page=${page-1}`)
  }

  getById(id: string){
    return this._httpClient.get(`${this.URL}people/${id}`)
  }

  getParticipatingMovie(person):  Observable<any[]>{
    return this._httpClient.get<any[]>(`${this.URL}people/part${person.id}`)
  }

  personUpdate(id: string, person){
    return this._httpClient.put(`${this.URL}people/${id}`, person)
  }

}
