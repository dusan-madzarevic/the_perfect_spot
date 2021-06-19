import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import {LoginComponent} from './components/login/login.component';
import {SignUpComponent} from './components/sign-up/sign-up.component';
import {RestaurantsComponent} from './components/restaurants/restaurants.component';
import {RecipesComponent} from './components/recipes/recipes.component';
import {ProfileComponent} from './components/profile/profile.component';
import {ChangePasswordComponent} from './components/change-password/change-password.component';
import {RestaurantFormComponent} from './components/restaurant-form/restaurant-form.component';
import {RestaurantDetailsComponent} from './components/restaurant-details/restaurant-details.component';
import {EditRestaurantComponent} from './components/edit-restaurant/edit-restaurant.component';
import {CreateRestaurantComponent} from './components/create-restaurant/create-restaurant.component';
import {RecipeDetailsComponent} from './components/recipe-details/recipe-details.component';
import {RestaurantReportsComponent} from './components/restaurant-reports/restaurant-reports.component';
import {RecipeFormComponent} from './components/recipe-form/recipe-form.component';
import {AlarmsComponent} from './components/alarms/alarms.component';
import { RecipeReportsComponent } from './components/recipe-reports/recipe-reports.component';
import { CreateRecipeComponent } from './components/create-recipe/create-recipe.component';
import { EditRecipeComponent } from './components/edit-recipe/edit-recipe.component';

const routes: Routes = [
  { path: '', redirectTo: 'restaurants', pathMatch: 'full' },
  { path: 'login', component: LoginComponent },
  {path: 'sign-up', component: SignUpComponent},
  { path: 'restaurants', component:RestaurantsComponent},
  {path: 'recipes', component:RecipesComponent},
  {path: 'profile', component:ProfileComponent},
  {path: 'change-password', component:ChangePasswordComponent},
  {path:'restaurant-recommendation',component:RestaurantFormComponent},
  {path: 'restaurant-reports',component:RestaurantReportsComponent},
  {path: 'recipe-reports',component:RecipeReportsComponent},
  {path:'recipe-details/:id',component:RecipeDetailsComponent},
  {path:'restaurant-details/:id',component:RestaurantDetailsComponent},
  {path:'recipe-recommendation',component:RecipeFormComponent},
  {path:'edit-restaurant/:id',component:EditRestaurantComponent},
  {path:'add-restaurant',component:CreateRestaurantComponent},
  {path:'edit-recipe/:id',component:EditRecipeComponent},
  {path:'add-recipe',component:CreateRecipeComponent},
  {path:'alarms',component:AlarmsComponent}];
@NgModule({

  imports: [RouterModule.forRoot(routes, {onSameUrlNavigation: 'reload'})],
  exports: [RouterModule]
})

export class AppRoutingModule { }
