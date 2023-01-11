import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class GetTrelloInfoService {

  constructor(private httpClient: HttpClient) { }

  public getUserTrelloData(body) {
    const headers = new HttpHeaders({
      'Content-Type': 'text/json'
    });
    const options = {
      headers
    };
    return this.httpClient.post<any>('/GitRepositoryAnalysisSystem/getUserTrello', body, options);
  }
}
