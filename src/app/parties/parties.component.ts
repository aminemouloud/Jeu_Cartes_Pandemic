import { Component, OnInit } from '@angular/core';
import { Partie } from '../modele/partie';
import { JoueurService } from '../services/joueur.services';
import { ActivatedRoute, Router } from '@angular/router';


@Component({
  selector: 'app-parties',
  templateUrl: './parties.component.html',
  styleUrls: ['./parties.component.css']
})
export class PartiesComponent implements OnInit {
  parties:Partie[]=[];
  pollingInterval: any;
  partie?:Partie
  
  constructor(private route: ActivatedRoute,private joueurService:JoueurService, private router:Router) { }

  ngOnInit(): void {
    this.route.queryParams.subscribe((params) => {
      if (params.parties) {
        this.parties = JSON.parse(params.parties);
      }
    });

    this.refreshPartie();
    this.pollingInterval = setInterval(() => {
      this.refreshPartie();
    }, 5000)
  }

  refreshPartie() {
    if(this.parties){
      this.joueurService.getLesPartieARejoindre().subscribe((data) => {
        this.parties = data;
      });
    }
  }

  rejoindrePartie(partie: Partie) {
    var joueur = JSON.parse(localStorage.getItem('currentUser') || '{}');
    var JoueurnomJoueur = joueur.nomJoueur;
    let body={
      idPartie: partie.idPartie,
      nomJoueur: JoueurnomJoueur,
    };
    this.joueurService.rejoindrePartie(body).subscribe((data)=>{
      this.partie = data;
      this.router.navigate(['/creerPartie'], { queryParams: { partie: JSON.stringify(this.partie) } })
      });
    
  }

  quitter(){this.router.navigate(['/login']);localStorage.clear();}

  ngOnDestroy(){
    clearInterval(this.pollingInterval);
  }
}
