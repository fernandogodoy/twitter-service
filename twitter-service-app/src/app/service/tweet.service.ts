import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class TweetService {

  constructor(private http: HttpClient) { }

  getByTime() :  Observable<any>{
    return this.http.get("https://twitter-filter-service.herokuapp.com/tweet/list/grouped-time");
  }

  getByLanguage() :  Observable<any>{
    return this.http.get("https://twitter-filter-service.herokuapp.com/tweet/list/grouped-language");
  }
}
