import {HttpClient, HttpHeaders, HttpParams} from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Router } from '@angular/router';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';
import { GradeRecipeModel } from '../model/grade.recipe.model';
import {GradeRestaurantModel} from '../model/grade.restaurant.model';


@Injectable({
  providedIn: 'root'
})
export class GradeService {

  private readonly gradeByRestAPI: string = "restaurant-grade/by-restaurant/";
  private readonly gradeRestaurantAPI: string = "restaurant-grade/grade";
  private readonly gradeByRecipeAPI: string = "recipe-grade/by-recipe/";
  private readonly gradeRecipeAPI: string = "recipe-grade/grade";

  constructor(private http: HttpClient, private route: Router) {
  }

  getGradeForRestaurantByLoggedUser(restId: any): Observable<any> {
    const headers = new HttpHeaders({
      'Content-Type': 'application/json',
      'Authorization': 'Bearer ' + localStorage.getItem("accessToken")
    });
    let params = new HttpParams().set('restId', restId).set("email",localStorage.getItem("email"));
    return this.http.get(environment.APP + this.gradeByRestAPI, {params:params,headers: headers});
  }

  gradeRestaurant(restId: any, grade: number): Observable<any> {
    const headers = new HttpHeaders({
      'Content-Type': 'application/json',
      'Authorization': 'Bearer ' + localStorage.getItem("accessToken")
    });
    const gradeRest: GradeRestaurantModel = {
      email: localStorage.getItem("email"),
      grade: grade,
      restId: restId
    }
    return this.http.post(environment.APP + this.gradeRestaurantAPI, gradeRest, {headers: headers});
  }

  getGradeForRecipeByLoggedUser(restId: any): Observable<any> {
    const headers = new HttpHeaders({
      'Content-Type': 'application/json',
      'Authorization': 'Bearer ' + localStorage.getItem("accessToken")
    });
    let params = new HttpParams().set('recipeId', restId).set("email",localStorage.getItem("email"));
    return this.http.get(environment.APP + this.gradeByRecipeAPI, {params:params,headers: headers});
  }

  gradeRecipe(recipeId: any, grade: number): Observable<any> {
    const headers = new HttpHeaders({
      'Content-Type': 'application/json',
      'Authorization': 'Bearer ' + localStorage.getItem("accessToken")
    });
    const gradeRest: GradeRecipeModel = {
      email: localStorage.getItem("email"),
      grade: grade,
      recipeId: recipeId
    }
    return this.http.post(environment.APP + this.gradeRecipeAPI, gradeRest, {headers: headers});
  }

}
