import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {Router} from '@angular/router';
import {LoginModel} from '../model/login.model';
import {LoginResponse} from '../model/login.response.model';
import {Observable} from 'rxjs';
import {Role, TokenModel} from '../model/token.model';
import {environment} from '../../environments/environment';
import {SignupResponse} from '../model/SignupResponse';


@Injectable({
  providedIn: 'root'
})
export class AuthService {

  private readonly signUpPath: string = 'auth/sign-up';
  private readonly logInPath: string = 'auth/log-in';
  constructor(private http: HttpClient, private route: Router) { }

  login(data: any): Observable<LoginResponse> {

    const headers = new HttpHeaders({
      'Content-Type': 'application/json'
    });

    const message = JSON.stringify(data);

    return this.http.post<LoginResponse>(environment.APP + this.logInPath, message, { headers: headers });
  }

  signUp(signupDto: string):Observable<any> {
    const headers = new HttpHeaders({
      'Content-Type': 'application/json'
    });

    return this.http.post<SignupResponse>(environment.APP + this.signUpPath, signupDto, {headers: headers})
  }

  isAdmin(): boolean {
    let authorities = this.getUserAuthorities();
    let role = "ROLE_ADMIN";

    for(let a of authorities) {
      if(role === a.name)
        return true;
    }


    return false;
  }

  isUser(): boolean {
    let authorities = this.getUserAuthorities();
    let role = "ROLE_USER";
    for(let a of authorities) {
      if(role === a.name)
        return true;
    }

    return false;
  }

  getUserAuthorities(): Array<Role> {
    let token = this.getToken();
    if(token) {
      let model = this.decodeToken(token);
      return model?.authority ? model.authority : [];
    } else {
      return [];
    }
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
    console.log("logout")
    localStorage.removeItem("accessToken");
  }
}
