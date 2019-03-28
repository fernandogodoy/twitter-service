import { Component, OnInit } from '@angular/core';
import { TopFiveProfilesService } from '../service/top-five-profiles.service'
import { TopProfiles } from '../model/top-profile'

@Component({
  selector: 'app-top-five-profiles',
  templateUrl: './top-five-profiles.component.html',
  styleUrls: ['./top-five-profiles.component.css']
})
export class TopFiveProfilesComponent implements OnInit {

  topFiveProfiles : Array<TopProfiles>

  constructor(private topFiveProfilesService : TopFiveProfilesService) { }

  ngOnInit() {
    console.log("Buscando profiles");
    this.topFiveProfilesService.getTopFiveProfiles().subscribe(data => this.topFiveProfiles = data);
  }

}
