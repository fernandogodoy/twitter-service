import { Component, OnInit } from '@angular/core';
import {TwitterServiceService} from '../service/twitter-service.service'

@Component({
  selector: 'app-hashtag-list',
  templateUrl: './hashtag-list.component.html',
  styleUrls: ['./hashtag-list.component.css']
})
export class HashtagListComponent implements OnInit {

  hashtags :  Array<any>;

  constructor(private twitterServiceService: TwitterServiceService) { }

  ngOnInit() {
    console.log("Buscando Hashtags");
    this.twitterServiceService.getAllHashtags().subscribe(data => this.hashtags = data);
  }

}
