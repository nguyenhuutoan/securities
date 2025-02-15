import { Injectable } from '@angular/core';
import  { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';

const AUTH_API = 'http://localhost:8080/api/auth/';

const httpOptions = {
  headers: new HttpHeaders({'Content-Type': 'application/json'} )
};

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  constructor(public http: HttpClient) { }

  login(email: string, password: string): Observable<any> {
    console.log('email: ' + email);
    console.log('password: ' + password);
    return this.http.post(
      AUTH_API + 'signin',
      { email, password },
      httpOptions
    );
  }

  logout(): Observable<any> {
      return this.http.post(
        AUTH_API + 'signout', { }, httpOptions
      )
  }

}
