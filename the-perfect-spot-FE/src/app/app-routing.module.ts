import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { HomePageComponent } from './components/home-page/home-page.component';
import {LoginComponent} from './components/login/login.component';
import {SignUpComponent} from './components/sign-up/sign-up.component';
import {RestaurantsComponent} from './components/restaurants/restaurants.component';
import {RecipesComponent} from './components/recipes/recipes.component';
import {ProfileComponent} from './components/profile/profile.component';

const routes: Routes = [
  { path: '', redirectTo: 'home', pathMatch: 'full' },
  { path: 'home', component: HomePageComponent},
  { path: 'login', component: LoginComponent },
  {path: 'sign-up', component: SignUpComponent},
  { path: 'restaurants', component:RestaurantsComponent},
  {path: 'recipes', component:RecipesComponent},
  {path: 'profile', component:ProfileComponent}];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})

export class AppRoutingModule { }
