import { BaseService } from './base/base.service';
import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class GenresService extends BaseService{

  constructor(httpClient: HttpClient) {
    super(httpClient);
  }

  getDiscover(): Observable<any[]> {
    return this._httpClient.get<any[]>(`${this.URL}genres`)
  }
  
}
