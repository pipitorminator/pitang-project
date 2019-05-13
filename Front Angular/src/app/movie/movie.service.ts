import { BaseService } from '../services/base/base.service';
import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';

@Injectable()
export class MovieService extends BaseService {

  constructor(httpClient: HttpClient) {
    super(httpClient);
  }


  getDiscover(page) {
    return this._httpClient.get(`${this.URL}movies/?size=9&page=${page-1}`)
  }

  getById(id: string) {
    return this._httpClient.get(`${this.URL}movies/${id}`)
  }

  movieUpdate(id: string, movie){
    return this._httpClient.put(`${this.URL}movies/${id}`, movie)
  }
  movieDelete(id: string){
    return this._httpClient.delete(`${this.URL}movies/${id}`)

  }

}
