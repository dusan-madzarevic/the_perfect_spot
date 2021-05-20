import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Router } from '@angular/router';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class RecipeService {

  private readonly requestRecipes: string = "recipe/process";

  constructor(private http: HttpClient, private route: Router) { }

  getRecipes(data: any): Observable<any>{
    const headers = new HttpHeaders({
      'Content-Type': 'application/json'
    });

    return this.http.post(environment.APP+ this.requestRecipes, data, {headers:headers});
  }

}
