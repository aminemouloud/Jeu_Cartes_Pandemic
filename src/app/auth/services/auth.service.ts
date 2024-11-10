import { Joueur } from './../../modele/joueur';
import { HttpClient, HttpHeaders, HttpResponse } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Router } from '@angular/router';
import { TokenService } from './token.service';
import { environment } from 'src/environments/environment';
import { BehaviorSubject, Observable } from 'rxjs';
import { Credentials } from 'src/app/modele/credentials';


@Injectable({
  providedIn: 'root'
})
export class AuthService {
  static readonly TOKEN_STORAGE_KEY = 'token';
  private apiServerUrl=environment.apiBaseUrl;
  isLoggedIn: boolean=false;

  private currentUserSubject: BehaviorSubject<Joueur>;
  public currentUser: Observable<Joueur>;

  constructor(private router: Router, private tokenService: TokenService,private http: HttpClient) {
    this.currentUserSubject = new BehaviorSubject<Joueur>(JSON.parse(localStorage.getItem('currentUser')|| '{}'));
    this.currentUser = this.currentUserSubject.asObservable();
   }

   
  public login(credentials: Credentials): void {
    this.tokenService.getResponseHeaders(credentials)
    .subscribe((res: HttpResponse<any>) => {
      console.log(res.headers.get('Authorization'))
      this.saveToken(res.headers.get('Authorization')|| '{}');
      this.router.navigate(['/accueil'])
    });
  }
 
    logout() {
    // remove user from local storage to log user out
    localStorage.removeItem('currentUser');
    localStorage.clear()
    //this.currentUserSubject.next(null);
    this.router.navigate(['/login']);
}
  private saveToken(token: string){
    localStorage.setItem(AuthService.TOKEN_STORAGE_KEY, token);
    this.getJoueurConnected();
  }

  public getToken(): string | null {
  
    return localStorage.getItem(AuthService.TOKEN_STORAGE_KEY);
  }

 

  public getJoueurConnected(){
    return this.http.get(`${this.apiServerUrl}/pandemic/joueurLogged`).subscribe((joueur:any)=>
        {
          if(joueur!=null){
            localStorage.setItem('currentUser', JSON.stringify(joueur));
            this.currentUserSubject.next(joueur);
          
          }
            return joueur;
      });
  }
 
  public get currentUserValue(): Joueur {
    return this.currentUserSubject.value;

}

  
 
 
}
