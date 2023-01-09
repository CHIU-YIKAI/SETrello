import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class CreateTrelloService {

  constructor(private httpClient: HttpClient) { }

  public createBoard(body) {
    const headers = new HttpHeaders({
      'Content-Type': 'text/json'
    });
    const options = {
      headers
    };
    return this.httpClient.post<any>('/GitRepositoryAnalysisSystem/CreateTrelloProject', body, options);
  }
}
