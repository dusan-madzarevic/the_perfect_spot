import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { LoginComponent } from './components/login/login.component';
import { NavigationBarComponent } from './components/navigation-bar/navigation-bar.component';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';
import {HttpClientModule} from '@angular/common/http';
import { HomePageComponent } from './components/home-page/home-page.component';
import { RecipeFormComponent } from './components/recipe-form/recipe-form.component';
import { SignUpComponent } from './components/sign-up/sign-up.component';
import { RestaurantsComponent } from './components/restaurants/restaurants.component';
import { RecipesComponent } from './components/recipes/recipes.component';
import { RestaurantFormComponent } from './components/restaurant-form/restaurant-form.component';
import { ProfileComponent } from './components/profile/profile.component';
import { RestaurantComponent } from './components/restaurant/restaurant.component';
import {MatPaginatorModule} from '@angular/material/paginator';
import {MatIconModule} from '@angular/material/icon';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { ChangePasswordComponent } from './components/change-password/change-password.component';
import {MatButtonModule} from '@angular/material/button';
import { FilterRestaurantsComponent } from './components/filter-restaurants/filter-restaurants.component';
import { NgSelectModule } from "@ng-select/ng-select";
import { NgMultiSelectDropDownModule } from 'ng-multiselect-dropdown';
import {MatSelectModule} from '@angular/material/select';
import { MatInputModule } from '@angular/material/input';
import {MatCheckboxModule} from '@angular/material/checkbox';
import { StarRatingComponent } from './components/star-rating/star-rating.component';
import {MatSnackBarModule} from '@angular/material/snack-bar';
import {MatTooltipModule} from '@angular/material/tooltip';
import { RestaurantDetailsComponent } from './components/restaurant-details/restaurant-details.component';
import { RecipeComponent } from './components/recipe/recipe.component';
import { FilterRecipesComponent } from './components/filter-recipes/filter-recipes.component';
import { RecipeDetailsComponent } from './components/recipe-details/recipe-details.component';
import { MatDialogModule} from "@angular/material/dialog";
import { RestaurantModalComponent } from './components/restaurant-modal/restaurant-modal.component';

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
    ProfileComponent,
    RestaurantComponent,
    ChangePasswordComponent,
    FilterRestaurantsComponent,
    StarRatingComponent,
    RestaurantDetailsComponent,
    RecipeComponent,
    FilterRecipesComponent,
    RecipeDetailsComponent,
    RestaurantModalComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    ReactiveFormsModule,
    MatPaginatorModule,
    BrowserAnimationsModule,
    MatIconModule,
    MatButtonModule,
    FormsModule,
    MatSelectModule,
    MatInputModule,
    MatCheckboxModule,
    MatSnackBarModule,
    MatTooltipModule,
    NgMultiSelectDropDownModule.forRoot(),
    MatDialogModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
