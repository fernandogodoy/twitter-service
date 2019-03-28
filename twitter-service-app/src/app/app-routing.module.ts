import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { HashtagListComponent } from './hashtag-list/hashtag-list.component';
import { AppComponent } from './app.component';
import { HomeComponent } from './home/home.component';


const routes: Routes = [
  {path: "", pathMatch: "full", redirectTo: "home"},
  {path: "home", component: HomeComponent},
  {path: "hashtag-list", component: HashtagListComponent}  
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }