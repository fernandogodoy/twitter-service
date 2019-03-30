import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { HashtagListComponent } from './hashtag-list/hashtag-list.component';
import { HomeComponent } from './home/home.component';
import { TopFiveProfilesComponent } from './top-five-profiles/top-five-profiles.component'
import { TweetByTimeComponent } from './tweet-by-time/tweet-by-time.component'
import { TweetComponent } from './tweet/tweet.component'


const routes: Routes = [
  {path: "/", pathMatch: "full", redirectTo: "home"},
  {path: "home", component: HomeComponent},
  {path: "hashtag-list", component: HashtagListComponent},
  {path: "top-five-profiles", component: TopFiveProfilesComponent},
  {path: "twitter-list-hour", component: TweetByTimeComponent},
  {path: "twitter-list-language", component: TweetComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }