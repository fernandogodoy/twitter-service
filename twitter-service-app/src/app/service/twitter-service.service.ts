import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class TwitterServiceService {

  constructor(private http: HttpClient) { }


  getAllHashtags() :  Observable<any> {
    return this.http.get("//localhost:4200/api/hashtag/list");
  }
}
