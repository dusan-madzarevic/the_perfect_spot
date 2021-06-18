import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Router } from '@angular/router';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class AlarmService {


  private readonly alarmAPI: string = "alarm/by-page/";

  constructor(private http: HttpClient, private route: Router) { }

  getAll(pageNum: number): Observable<any>{
    const headers = new HttpHeaders({
      'Content-Type': 'application/json',
      'Authorization' : 'Bearer ' + localStorage.getItem("accessToken")
    });

    return this.http.get(environment.APP+ this.alarmAPI + pageNum, {headers:headers});
  }

}
