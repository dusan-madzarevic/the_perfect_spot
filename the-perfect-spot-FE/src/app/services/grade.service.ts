import {HttpClient, HttpHeaders, HttpParams} from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Router } from '@angular/router';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';
import {GradeRestaurantModel} from '../model/grade.restaurant.model';


@Injectable({
  providedIn: 'root'
})
export class GradeService {

  private readonly gradeByRestAPI: string = "restaurant-grade/by-restaurant/";
  private readonly gradeRestaurantAPI: string = "restaurant-grade/grade";

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
}
