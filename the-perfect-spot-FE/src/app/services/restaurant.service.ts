import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Router } from '@angular/router';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class RestaurantService {

  private readonly requestRestaurantAPI: string = "restaurant/process";
  private readonly restaurantAPI: string = "restaurant/by-page/";

  constructor(private http: HttpClient, private route: Router) { }

  getAll(pageNum: number): Observable<any>{
    const headers = new HttpHeaders({
      'Content-Type': 'application/json',
      'Authorization' : 'Bearer ' + localStorage.getItem("accessToken")
    });

    return this.http.get(environment.APP+ this.restaurantAPI + pageNum, {headers:headers});
  }

}
