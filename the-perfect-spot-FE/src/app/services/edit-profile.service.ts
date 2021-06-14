import {HttpClient, HttpHeaders} from "@angular/common/http";
import {Router} from "@angular/router";
import {Injectable} from '@angular/core';
import {environment} from '../../environments/environment';
import {userDto} from "../model/userDto";
import {Observable} from "rxjs";


@Injectable({
  providedIn: 'root'
})
export class EditProfileService{
  private  readonly editUserPath = "users/editProfile/";
  private  readonly getUserPath = "users/loggedInUser";

  constructor(private http: HttpClient) {}

  getUser():Observable<any>{
    const headers = new HttpHeaders({
      'Content-Type': 'application/json',
      'Authorization' : 'Bearer ' + localStorage.getItem("accessToken")
    });
    return this.http.get<userDto>(environment.APP + this.getUserPath, {headers: headers});
  }

  editUser(userDto: string, id: number):Observable<any>{
    const headers = new HttpHeaders({
      'Content-Type': 'application/json',
      'Authorization' : 'Bearer ' + localStorage.getItem("accessToken")
    });
    return this.http.put<userDto>(environment.APP + this.editUserPath + id, userDto, {headers: headers});
  }

}

