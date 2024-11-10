import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { JoueurService } from '../services/joueur.services';

@Component({
  selector: 'app-accueil',
  templateUrl: './accueil.component.html',
  styleUrls: ['./accueil.component.css']
})
export class AccueilComponent implements OnInit {

  constructor(private router:Router,private joueurService:JoueurService ) { }

  
  ngOnInit(): void {
    
  }
  allerDansMenu(){
 this.router.navigate(['/menu'])
  }

  seDeconnecter(){
    // ne pas oublié de se déconnecter
    this.router.navigate(['/login'])
  }


}
