import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class GetBoardInfoOfChosenProjectService {

  constructor(private httpClient: HttpClient) { }


  getBoardDataOfProject(body) {
      const headers = new HttpHeaders({
        'Content-Type': 'text/json'
      });
      const options = {
        headers
      };
      const r = this.httpClient.post<any>('/GitRepositoryAnalysisSystem/getUserTrello', body, options);
      return r;
    }
}
