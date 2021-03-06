import {HttpClient, HttpHeaders, HttpParams} from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Router } from '@angular/router';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';
import {FilterObject} from '../model/filter.object.model';
import {PreferencesModel} from '../model/preferences.model';

@Injectable({
  providedIn: 'root'
})
export class ReportService {


  private readonly restaurantBestGradedAPI: string = "restaurant/report/best-graded";
  private readonly restaurantBestGradedLMAPI: string = "restaurant/report/best-graded-last-month";
  private readonly restaurantMostRecommAPI: string = "restaurant/report/most-recommended";
  private readonly restaurantInGradeRangeAPI: string = "restaurant/report/in-grade-range";
  private readonly restaurantInDateRangeAPI: string = "restaurant/report/average-grade-in-data-range";

  private readonly recipeBestGradedAPI: string = "recipe/report/best-graded";
  private readonly recipeBestGradedLMAPI: string = "recipe/report/best-graded-last-month";
  private readonly recipeMostRecommAPI: string = "recipe/report/most-recommended";
  private readonly recipeInGradeRangeAPI: string = "recipe/report/in-grade-range";
  private readonly recipeInDateRangeAPI: string = "recipe/report/average-grade-in-data-range";

  constructor(private http: HttpClient) { }

  getBestGraded(): Observable<any>{
    const headers = new HttpHeaders({
      'Content-Type': 'application/json',
      'Authorization' : 'Bearer ' + localStorage.getItem("accessToken")
    });

    return this.http.get(environment.APP+ this.restaurantBestGradedAPI, {headers:headers});
  }

  getBestGradedLastMonth(): Observable<any>{
    const headers = new HttpHeaders({
      'Content-Type': 'application/json',
      'Authorization' : 'Bearer ' + localStorage.getItem("accessToken")
    });

    return this.http.get(environment.APP+ this.restaurantBestGradedLMAPI, {headers:headers});
  }

  getMostRecommended(): Observable<any>{
    const headers = new HttpHeaders({
      'Content-Type': 'application/json',
      'Authorization' : 'Bearer ' + localStorage.getItem("accessToken")
    });

    return this.http.get(environment.APP+ this.restaurantMostRecommAPI, {headers:headers});
  }

  getInGradeRange(minGrade, maxGrade): Observable<any>{
    const headers = new HttpHeaders({
      'Content-Type': 'application/json',
      'Authorization' : 'Bearer ' + localStorage.getItem("accessToken")
    });
    const params = new HttpParams().set('minGrade', minGrade).set('maxGrade',maxGrade);
    return this.http.get(environment.APP+ this.restaurantInGradeRangeAPI, {params:params,headers:headers});
  }

  getInDateRange(dateFrom, dateTo): Observable<any>{
    const headers = new HttpHeaders({
      'Content-Type': 'application/json',
      'Authorization' : 'Bearer ' + localStorage.getItem("accessToken")
    });
    const params = new HttpParams().set('dateFrom', dateFrom).set('dateTo', dateTo);

    return this.http.get(environment.APP+ this.restaurantInDateRangeAPI, {params:params,headers:headers});
  }

  getBestGradedRecipes(): Observable<any> {
    const headers = new HttpHeaders({
      'Content-Type': 'application/json',
      'Authorization' : 'Bearer ' + localStorage.getItem("accessToken")
    });

    return this.http.get(environment.APP+ this.recipeBestGradedAPI, {headers:headers});
  }

  getBestGradedLastMonthRecipes(): Observable<any> {
    const headers = new HttpHeaders({
      'Content-Type': 'application/json',
      'Authorization' : 'Bearer ' + localStorage.getItem("accessToken")
    });

    return this.http.get(environment.APP+ this.recipeBestGradedLMAPI, {headers:headers});
  }

  getMostRecommendedRecipes(): Observable<any> {
    const headers = new HttpHeaders({
      'Content-Type': 'application/json',
      'Authorization' : 'Bearer ' + localStorage.getItem("accessToken")
    });

    return this.http.get(environment.APP+ this.recipeMostRecommAPI, {headers:headers});
  }

  getInGradeRangeRecipes(minGrade, maxGrade): Observable<any> {
    const headers = new HttpHeaders({
      'Content-Type': 'application/json',
      'Authorization' : 'Bearer ' + localStorage.getItem("accessToken")
    });
    const params = new HttpParams().set('minGrade', minGrade).set('maxGrade',maxGrade);
    return this.http.get(environment.APP+ this.recipeInGradeRangeAPI, {params:params,headers:headers});
  }

  getInDateRangeRecipes(dateFrom, dateTo): Observable<any> {
    const headers = new HttpHeaders({
      'Content-Type': 'application/json',
      'Authorization' : 'Bearer ' + localStorage.getItem("accessToken")
    });
    const params = new HttpParams().set('dateFrom', dateFrom).set('dateTo', dateTo);

    return this.http.get(environment.APP+ this.recipeInDateRangeAPI, {params:params,headers:headers});
  }

}
