import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders, HttpResponse } from '@angular/common/http';
import { environment } from 'src/environments/environment';
import { Credentials } from 'src/app/modele/credentials';
import { Observable } from 'rxjs';

const httpOptions = {
  headers: new HttpHeaders({
    'Content-Type': 'application/json'
  })
};
@Injectable({
  providedIn: 'root'
})
export class TokenService {

  private apiServerUrl=environment.apiBaseUrl;
  constructor(private http: HttpClient) { }

  public getResponseHeaders(credentials: Credentials): Observable<HttpResponse<any>> {
    const formData = {
      'nomJoueur': credentials.nomJoueur,
      'mdp': credentials.password
    }
    return this.http.post(`${this.apiServerUrl}/pandemic/login`, JSON.stringify(formData), { ...httpOptions, observe: 'response'})
  }
 
}
