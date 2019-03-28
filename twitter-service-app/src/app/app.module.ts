import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { HttpClientModule } from '@angular/common/http'; 
import { AppRoutingModule } from './app-routing.module';

import { AppComponent } from './app.component';
import { HashtagListComponent } from './hashtag-list/hashtag-list.component';
import { HeaderComponent } from './header/header.component';
import { HomeComponent } from './home/home.component';
import { TopFiveProfilesComponent } from './top-five-profiles/top-five-profiles.component';
import { TweetComponent } from './tweet/tweet.component';
import { TweetByTimeComponent } from './tweet-by-time/tweet-by-time.component';

@NgModule({
  declarations: [
    AppComponent,
    HashtagListComponent,
    HeaderComponent,
    HomeComponent,
    TopFiveProfilesComponent,
    TweetComponent,
    TweetByTimeComponent
  ],
  imports: [
    BrowserModule,
    HttpClientModule,
    AppRoutingModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
