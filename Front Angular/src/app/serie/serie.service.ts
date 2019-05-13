import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { BaseService } from '../services/base/base.service';

@Injectable()
export class SerieService extends BaseService {

  constructor(httpClient: HttpClient) {
    super(httpClient);
  }

  getDiscover(page) {
    return this._httpClient.get(`${this.URL}tv/?size=9&page=${page-1}`)
  }

  getById(id: string) {
    return this._httpClient.get(`${this.URL}tv/${id}`)
  }

  serieUpdate(id: string, serie){
    return this._httpClient.put(`${this.URL}tv/${id}`, serie)
  }

  serieDelete(id: string){
    return this._httpClient.delete(`${this.URL}tv/${id}`);
  }

  
}