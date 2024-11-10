import { Plateau } from './../modele/Plateau';
import { JoueurService } from './../services/joueur.services';
import { Component, OnInit } from '@angular/core';
import { Partie } from '../modele/partie';
import { ActivatedRoute, Router } from '@angular/router';
import { PlateauInitial } from '../modele/PlateauInitial';
import { Images } from '../modele/images';


@Component({
  selector: 'app-creation-partie',
  templateUrl: './creation-partie.component.html',
  styleUrls: ['./creation-partie.component.css']
})
export class CreationPartieComponent implements OnInit {
  partie?: Partie;
  plateau?:Plateau;
  joueurService: JoueurService
  pollingInterval: any
  

  constructor(private route: ActivatedRoute,private serviceJoueur:JoueurService,private router:Router) { 
    this.joueurService = serviceJoueur
  }

  ngOnInit(): void {
    this.route.queryParams.subscribe((params) => {
      if (params.partie) {
        this.partie = JSON.parse(params.partie);
      }
    });
    console.log("---- je suis là" +this.partie)
    this.refreshPartie();
    this.pollingInterval = setInterval(() => {
      this.refreshPartie();
    }, 5000)
  }
 
  lancerPartie() {
    if(this.partie){
      this.joueurService.initialiserPartie(this.partie).subscribe((data: PlateauInitial) => {  
        const plateau: Plateau = {
          idPartie: data.idPartie,
          etatPartie: data.etatPartie,
          lesJoueurs: data.lesJoueurs,
          lesVilles: data.lesVilles,
          carteEpidemie: data.carteEpidemie,
          cartesPropagation: data.cartesPropagation,
          defausse_cartesJoueur: data.defausse_cartesJoueur,
          defausse_carteDePropagation: data.defausse_carteDePropagation,
          cartesJoueur: data.cartesJoueur,
          nomJoueur: data.nomJoueur,
          cartes_en_main : data.cartes_en_main
        }
        const images: Images = {
          imageData: data.imageData
        }
        this.router.navigate(['/plateau'], { state: { plateau, images }})
      });
    }
  }

  refreshPartie() {
    if(this.partie){
      this.joueurService.getPartie(this.partie).subscribe((data) => {
        this.partie = data;
      })
    }
  }
  
  quitter(){this.router.navigate(['/login']);localStorage.clear();}
  retour(){
    this.router.navigate(['/parties'])
  }
  ngOnDestroy(){
    clearInterval(this.pollingInterval);
  }
}
