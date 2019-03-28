import { Component, OnInit } from '@angular/core';
import { TweetService } from '../service/tweet.service'

@Component({
  selector: 'app-tweet-by-time',
  templateUrl: './tweet-by-time.component.html',
  styleUrls: ['./tweet-by-time.component.css']
})
export class TweetByTimeComponent implements OnInit {

  tweets : Array<any>;

  constructor(private tweetService: TweetService) { }

  ngOnInit() {
    this.tweetService.getByTime().subscribe(data => this.tweets = data);
  }

}
