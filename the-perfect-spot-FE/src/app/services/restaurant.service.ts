import {HttpClient, HttpHeaders, HttpParams} from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Router } from '@angular/router';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';
import {FilterObject} from '../model/filter.object.model';
import {PreferencesModel} from '../model/preferences.model';
import {EditRestaurantModel} from '../model/edit.restaurant.model';
import {RestaurantModel} from '../model/restaurant.model';

@Injectable({
  providedIn: 'root'
})
export class RestaurantService {

  private readonly requestRestaurantAPI: string = "restaurant/process";
  private readonly restaurantAPI: string = "restaurant/by-page/";
  private readonly editRestaurantAPI: string = "restaurant/";
  private readonly addRestaurantAPI: string = "restaurant";
  private readonly oneRestaurantAPI: string = "restaurant/";
  private readonly restaurantFilterAPI: string = "restaurant/filter/by-page/";
  private readonly restaurantSearchAPI: string = "restaurant/search/by-page/";
  private readonly recommendRestaurantAPI: string = "restaurant/process";

  constructor(private http: HttpClient, private route: Router) { }

  getAll(pageNum: number): Observable<any>{
    const headers = new HttpHeaders({
      'Content-Type': 'application/json',
      'Authorization' : 'Bearer ' + localStorage.getItem("accessToken")
    });

    return this.http.get(environment.APP+ this.restaurantAPI + pageNum, {headers:headers});
  }

  getByPageFilter(pageIndex: number, prices: any, cuisines: any) : Observable<any>{
    const headers = new HttpHeaders({
      'Content-Type': 'application/json',
      'Authorization' : 'Bearer ' + localStorage.getItem("accessToken")
    });
    let params = new HttpParams().set('cuisines', cuisines).set("prices",prices);

    return this.http.get(environment.APP+ this.restaurantFilterAPI + pageIndex, {params:params,headers:headers});
  }

  getByPageSearch(pageIndex: number, data: any) : Observable<any>{
    const headers = new HttpHeaders({
      'Content-Type': 'application/json',
      'Authorization' : 'Bearer ' + localStorage.getItem("accessToken")
    });
    let params = new HttpParams().set('restName', data);

    return this.http.get(environment.APP+ this.restaurantSearchAPI + pageIndex, {params:params,headers:headers});
  }

  recommendRestaurant(preferences: PreferencesModel):Observable<any>{
    const headers = new HttpHeaders({
      'Content-Type': 'application/json',
      'Authorization' : 'Bearer ' + localStorage.getItem("accessToken")
    });
    return this.http.post(environment.APP + this.recommendRestaurantAPI, preferences, {headers:headers});
  }

  getOneById(id:any):Observable<any>{
    const headers = new HttpHeaders({
      'Content-Type': 'application/json',
      'Authorization' : 'Bearer ' + localStorage.getItem("accessToken")
    });

    return this.http.get(environment.APP+ this.oneRestaurantAPI + id, {headers:headers});
  }

  update(restaurant: EditRestaurantModel, id:any):Observable<any>{
    const headers = new HttpHeaders({
      'Content-Type': 'application/json',
      'Authorization' : 'Bearer ' + localStorage.getItem("accessToken")
    });

    return this.http.post(environment.APP + this.editRestaurantAPI + id,restaurant, {headers:headers});
  }

  create(restaurant: RestaurantModel):Observable<any> {
    const headers = new HttpHeaders({
      'Content-Type': 'application/json',
      'Authorization' : 'Bearer ' + localStorage.getItem("accessToken")
    });

    return this.http.post(environment.APP + this.addRestaurantAPI , restaurant, {headers:headers});
  }
}
