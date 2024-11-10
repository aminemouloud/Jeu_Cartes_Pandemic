import { Component, OnInit } from '@angular/core';
import { JoueurService } from '../services/joueur.services';
import { Router } from '@angular/router';

@Component({
  selector: 'app-compte',
  templateUrl: './compte.component.html',
  styleUrls: ['./compte.component.css']
})
export class CompteComponent implements OnInit {
  nomJoueur:String="";
  mdp:String="";
  displayBasic: boolean=false;
  constructor(private joueurService:JoueurService, private router:Router) { }

  ngOnInit(): void {
  }
  
  showBasicDialog() {
    this.displayBasic = true;
    setTimeout(() => {this.displayBasic = false}, 2500);
  }

  quitter(){
    this.router.navigate(['/login'])
  }
  onCreateCompte(){
    let body = {   
      nomJoueur:this.nomJoueur,
      mdp:this.mdp
  } 
  this.joueurService.creerCompte(body).subscribe((result)=>{this.router.navigate(['/login'])})
  }

}
