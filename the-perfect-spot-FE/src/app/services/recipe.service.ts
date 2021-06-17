import { HttpClient, HttpHeaders, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Router } from '@angular/router';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class RecipeService {

  private readonly requestRecipes: string = "recipe/process";
  private readonly allRecipes: string = "recipe/by-page/";
  private readonly oneRecipe: string = "recipe/";
  private readonly recipeFilter: string = "recipe/filter/by-page/";
  private readonly recipeSearch: string = "recipe/search/by-page/";

  constructor(private http: HttpClient, private route: Router) { }

  getRecipes(data: any): Observable<any>{
    const headers = new HttpHeaders({
      'Content-Type': 'application/json',
      'Authorization' : 'Bearer ' + localStorage.getItem("accessToken")
    });

    return this.http.post(environment.APP+ this.requestRecipes, data, {headers:headers});
  }

  getAll(pageNum: number): Observable<any>{
    const headers = new HttpHeaders({
      'Content-Type': 'application/json',
      'Authorization' : 'Bearer ' + localStorage.getItem("accessToken")
    });

    return this.http.get(environment.APP+ this.allRecipes + pageNum, {headers:headers});
  }

  getByPageFilter(pageIndex: number, dishTypes: any) : Observable<any>{
    const headers = new HttpHeaders({
      'Content-Type': 'application/json',
      'Authorization' : 'Bearer ' + localStorage.getItem("accessToken")
    });
    let params = new HttpParams().set('dishTypes', dishTypes);

    return this.http.get(environment.APP+ this.recipeFilter + pageIndex, {params:params,headers:headers});
  }

  getByPageSearch(pageIndex: number, data: any) : Observable<any>{
    const headers = new HttpHeaders({
      'Content-Type': 'application/json',
      'Authorization' : 'Bearer ' + localStorage.getItem("accessToken")
    });
    let params = new HttpParams().set('restName', data);

    return this.http.get(environment.APP+ this.recipeSearch + pageIndex, {params:params,headers:headers});
  }

  getOneById(id:any):Observable<any>{
    const headers = new HttpHeaders({
      'Content-Type': 'application/json',
      'Authorization' : 'Bearer ' + localStorage.getItem("accessToken")
    });

    return this.http.get(environment.APP+ this.oneRecipe + id, {headers:headers});
  }

}
