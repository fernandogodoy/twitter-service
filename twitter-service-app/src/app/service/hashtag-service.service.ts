import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class HashtagService {

  constructor(private http: HttpClient) { }


  getAllHashtags() :  Observable<any> {
    return this.http.get("https://twitter-filter-service.herokuapp.com/hashtag/list");
  }
}
