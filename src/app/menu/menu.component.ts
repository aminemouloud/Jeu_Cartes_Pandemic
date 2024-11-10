import { JoueurService } from './../services/joueur.services';
import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Partie } from 'src/app/modele/partie';

@Component({
  selector: 'app-menu',
  templateUrl: './menu.component.html',
  styleUrls: ['./menu.component.css']
})
export class MenuComponent implements OnInit {
  partie?:Partie
  parties?: Partie[]
  constructor(private router:Router,private joueurService:JoueurService) { }

  ngOnInit(): void {
  }

  revenirAumenu(){
    this.router.navigate(['/menu'])
  }

 
  creerPartie() {
    var joueur = JSON.parse(localStorage.getItem('currentUser') || '{}');
    var JoueurnomJoueur = joueur.nomJoueur;
    let body = {
      nomJoueur: JoueurnomJoueur,
    };
    this.joueurService.creerPartie(body).subscribe((data) => {
      this.partie = data;
      this.router.navigate(['/creerPartie'], { queryParams: { partie: JSON.stringify(this.partie) } })
    });
  }


  rejoindrePartie(){
    this.joueurService.getLesPartieARejoindre()
    .subscribe((data) =>{
      this.parties = data
      this.router.navigate(['/parties'], {queryParams: {parties: JSON.stringify(this.parties)}})
    })
  }
  

 /* getpartiesSuspendues(){
    var joueur=JSON.parse(localStorage.getItem("currentUser")|| '{}');
    var JoueurnomJoueur=joueur.nomJoueur
    let body={
      nomJoueur:JoueurnomJoueur,
    }
    this.joueurService.getMesPartieSuspendues(body)
    .subscribe(data=>{console.log("----- "+data);localStorage.setItem("partiesSuspendues", JSON.stringify(data));})
    this.router.navigate(['/partieSuspendues'])
  }*/

  quitter(){this.router.navigate(['/login']);localStorage.clear();}

}
