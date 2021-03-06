import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { LoginComponent } from './components/login/login.component';
import { NavigationBarComponent } from './components/navigation-bar/navigation-bar.component';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';
import {HttpClientModule} from '@angular/common/http';
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
import {MatListModule} from '@angular/material/list';
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
import { StarRatingRecipeComponent } from './components/star-rating-recipe/star-rating-recipe.component';
import { RecipeModalComponent } from './components/recipe-modal/recipe-modal.component';
import { RestaurantReportsComponent } from './components/restaurant-reports/restaurant-reports.component';
import { RestaurantReportViewComponent } from './components/restaurant-report-view/restaurant-report-view.component';
import {MatDatepickerModule} from '@angular/material/datepicker';
import { MatNativeDateModule } from '@angular/material/core';
import { DateAdapter } from '@angular/material/core';
import { AgmCoreModule } from '@agm/core';
import { EditRestaurantComponent } from './components/edit-restaurant/edit-restaurant.component';
import { CreateRestaurantComponent } from './components/create-restaurant/create-restaurant.component';
import { AlarmsComponent } from './components/alarms/alarms.component';
import { RecipeReportsComponent } from './components/recipe-reports/recipe-reports.component';
import { RecipeReportViewComponent } from './components/recipe-report-view/recipe-report-view.component';
import { CreateRecipeComponent } from './components/create-recipe/create-recipe.component';
import { EditRecipeComponent } from './components/edit-recipe/edit-recipe.component';


@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    NavigationBarComponent,
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

    RestaurantModalComponent,
    RecipeComponent,
    FilterRecipesComponent,
    RecipeDetailsComponent,
    RestaurantModalComponent,
    StarRatingRecipeComponent,
    RecipeModalComponent,
    RestaurantReportsComponent,
    RestaurantReportViewComponent,
    EditRestaurantComponent,
    CreateRestaurantComponent,
    AlarmsComponent,
    RecipeReportsComponent,
    RecipeReportViewComponent,
    CreateRecipeComponent,
    EditRecipeComponent,
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
    MatNativeDateModule,
    MatDialogModule,
    MatListModule,
    MatDialogModule,
    MatDatepickerModule,
    AgmCoreModule.forRoot({
      apiKey: 'AIzaSyCTaSOkeIFqklyhUJHBfNSGLiBs5GN6a10'
    }),
    NgMultiSelectDropDownModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
