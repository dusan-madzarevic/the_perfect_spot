import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {Router} from '@angular/router';
import {LoginModel} from '../model/login.model';
import {LoginResponse} from '../model/login.response.model';
import {Observable} from 'rxjs';
import {TokenModel} from '../model/token.model';


@Injectable({
  providedIn: 'root'
})
export class AuthService {

  private readonly ENDPOINT_LOGIN: string = 'auth/log-in';

  constructor(private http: HttpClient, private route: Router) { }

  login(data: LoginModel): Observable<LoginResponse> {

    const headers = new HttpHeaders({
      'Content-Type': 'application/json'
    });

    const message = JSON.stringify(data);

    return this.http.post<LoginResponse>(this.ENDPOINT_LOGIN, message, { headers: headers });
  }

  isAdmin(): boolean {

    return false;
  }

  isUser(): boolean {
   //return localStorage.getItem('accessToken').role==='ROLE_USER';
    return true;
  }

  isLoggedIn(): boolean {
    let token = this.getToken();
    return !!token;
  }

  decodeToken(token: string): TokenModel | null {
    if (token) {
      let payload = token.split(".")[1];
      payload = window.atob(payload);
      return JSON.parse(payload);
    } else return null;
  }



  getToken(): string{
    return <string> localStorage.getItem("accessToken");
  }

  logout(): void {
    localStorage.removeItem("accessToken");
  }
}
