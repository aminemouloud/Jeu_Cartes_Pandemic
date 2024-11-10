import { HttpClient, HttpHeaders, HttpParams } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { Observable } from "rxjs";
import { environment } from "src/environments/environment";
import { Partie } from "src/app/modele/partie";
import { Plateau } from "../modele/Plateau";
import { PlateauInitial } from "../modele/PlateauInitial";

const httpOptions = {
    headers:new HttpHeaders({
      'Content-Type':'application/json',
  
    }),
   // observe: 'response' as 'response'
  };
  
  @Injectable({
    providedIn: 'root'
  })
  export class JoueurService {
    private apiServerUrl=environment.apiBaseUrl;
    
    constructor(private http: HttpClient) { }
  

    creerCompte(data: any): Observable<Partie[]> {
      return this.http.post<Partie[]>(`${this.apiServerUrl}/pandemic/inscription`, data, httpOptions); 
    }
    creerPartie(data: any): Observable<any> {
      return this.http.post(`${this.apiServerUrl}/pandemic/creerPartie`, data, httpOptions); 
    }
    
    getMesPartieSuspendues(data: any):Observable<Partie[]>{
      return this.http.post<Partie[]>(`${this.apiServerUrl}/pandemic/mesPartiesSuspendues`, data, httpOptions); 
    }

        
    getLesPartieARejoindre():Observable<Partie[]>{
      return this.http.get<Partie[]>(`${this.apiServerUrl}/pandemic/lesPartiesARejoindre`); 
    }
       
    rejoindreUnePartie(data: any):Observable<Partie[]>{
      return this.http.post<Partie[]>(`${this.apiServerUrl}/pandemic/rejoindrePartie`, data, httpOptions); 
    }

    getPartie(data: Partie):Observable<Partie>{
      return this.http.post<Partie>(`${this.apiServerUrl}/pandemic/maPartie`, data, httpOptions)
    }

    rejoindrePartie(data: any): Observable<any> {
      return this.http.post(`${this.apiServerUrl}/pandemic/rejoindrePartie`, data, httpOptions); 
    }

    initialiserPartie(data: Partie): Observable<PlateauInitial> {
      return this.http.post<PlateauInitial>(`${this.apiServerUrl}/pandemic/initialiserPartie`, data, httpOptions); 
    }

    actualiserPlateau(data: any): Observable<Plateau> {
      return this.http.post<Plateau>(`${this.apiServerUrl}/pandemic/actualiserPlateau`, data, httpOptions)
    }



    // Les Actions
    traiterMaladie(data: any): Observable<Plateau> {
      return this.http.post<Plateau>(`${this.apiServerUrl}/pandemic/traiterMaladie`,data,httpOptions)
    }
    construireStationRecherche(data: any): Observable<Plateau> {
      return this.http.post<Plateau>(`${this.apiServerUrl}/pandemic/construireStationRecherche`,data,httpOptions)
    }
    decouvrirRemede(data: any): Observable<Plateau> {
      return this.http.post<Plateau>(`${this.apiServerUrl}/pandemic/decouvrirRemede`,data,httpOptions)
    }
    deplacerStationRecherche(data: any): Observable<Plateau> {
      return this.http.post<Plateau>(`${this.apiServerUrl}/pandemic/deplacerStationRecherche`,data,httpOptions)
    }
    echangerCarte(data: any): Observable<Plateau> {
      return this.http.post<Plateau>(`${this.apiServerUrl}/pandemic/echangerCarte`,data,httpOptions)
    }
    piocherCarte(data: any) {
      return this.http.post<Plateau>(`${this.apiServerUrl}/pandemic/piocherCarte`,data,httpOptions)
    }
    passerTour(data: any): Observable<Plateau> {
      return this.http.post<Plateau>(`${this.apiServerUrl}/pandemic/passerTour`,data,httpOptions)
    }

    deplacementAvecVoiture(data: any): Observable<Plateau> {
      return this.http.post<Plateau>(`${this.apiServerUrl}/pandemic/deplacementAvecVoiture`,data,httpOptions)
    }
    deplacementVolCharter(data: any): Observable<Plateau> {
      return this.http.post<Plateau>(`${this.apiServerUrl}/pandemic/deplacementVolCharter`,data,httpOptions)
    }
    deplacementVolDirect(data: any): Observable<Plateau> {
      return this.http.post<Plateau>(`${this.apiServerUrl}/pandemic/deplacementVolDirect`,data,httpOptions)
    }
    deplacementNavette(data: any): Observable<Plateau> {
      return this.http.post<Plateau>(`${this.apiServerUrl}/pandemic/deplacementNavette`,data,httpOptions)
    }
  }