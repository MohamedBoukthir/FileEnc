import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";

const BASE_URL= "http://localhost:8080"

@Injectable({
  providedIn: 'root'
})
export class AuthenticationService {

  constructor(private http: HttpClient) {}

  register(registerRequest: any): Observable<any> {
    return this.http.post(BASE_URL + '/users/register', registerRequest);
  }

  login(loginRequest: any): Observable<any> {
    return this.http.post(BASE_URL + '/users/login', loginRequest);
  }
}
