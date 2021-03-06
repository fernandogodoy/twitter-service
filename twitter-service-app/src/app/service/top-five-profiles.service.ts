import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class TopFiveProfilesService {

  constructor(private http: HttpClient) { }

  getTopFiveProfiles() :  Observable<any> {
    return this.http.get("https://twitter-filter-service.herokuapp.com/twitter-profile/user-followers-count/top-5");
  }
}
