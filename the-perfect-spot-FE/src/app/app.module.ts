import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { LoginComponent } from './components/login/login.component';
import { NavigationBarComponent } from './components/navigation-bar/navigation-bar.component';
import {ReactiveFormsModule} from '@angular/forms';
import {HttpClientModule} from '@angular/common/http';
import { HomePageComponent } from './components/home-page/home-page.component';
import { RecipeFormComponent } from './components/recipe-form/recipe-form.component';
import { SignUpComponent } from './components/sign-up/sign-up.component';
import { FormsModule } from '@angular/forms';
import { RestaurantsComponent } from './components/restaurants/restaurants.component';
import { RecipesComponent } from './components/recipes/recipes.component';
import { RestaurantFormComponent } from './components/restaurant-form/restaurant-form.component';
import { ProfileComponent } from './components/profile/profile.component';

@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    NavigationBarComponent,
    HomePageComponent,
    RecipeFormComponent,
    SignUpComponent,
    RestaurantsComponent,
    RecipesComponent,
    RestaurantFormComponent,
    ProfileComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    ReactiveFormsModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
