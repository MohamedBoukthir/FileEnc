import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {Router} from "@angular/router";

const BASE_URL= "http://localhost:8080"

@Injectable({
  providedIn: 'root'
})
export class AuthenticationService {

  constructor(
    private http: HttpClient,
    private router: Router
    ) {}

  register(registerRequest: any): Observable<any> {
    return this.http.post(BASE_URL + '/users/register', registerRequest);
  }

  login(loginRequest: any): Observable<any> {
    return this.http.post(BASE_URL + '/users/login', loginRequest);
  }

  logout(): void {
    localStorage.clear();
    this.router.navigate(['/'])
    console.log('User logged out successfully.');
  }
}
