import { Component, OnInit } from '@angular/core';
import { TweetService } from '../service/tweet.service'

@Component({
  selector: 'app-tweet',
  templateUrl: './tweet.component.html',
  styleUrls: ['./tweet.component.css']
})
export class TweetComponent implements OnInit {

  tweets : Array<any>;

  constructor(private tweetService: TweetService) { }

  ngOnInit() {
    this.tweetService.getByLanguage().subscribe(data => this.tweets = data);
  }

}
