import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class TopFiveProfilesService {

  constructor(private http: HttpClient) { }

  getTopFiveProfiles() :  Observable<any> {
    return this.http.get("//localhost:8080/twitter-profile/user-followers-count/top-5");
  }
}
