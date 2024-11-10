import { Plateau } from './../modele/Plateau';
import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { JoueurService } from '../services/joueur.services';
import { Images } from '../modele/images';
import { PlateauInitial } from '../modele/PlateauInitial';
import { ImageService } from '../services/image.service';

@Component({
  selector: 'app-plateau',
  templateUrl: './plateau.component.html',
  styleUrls: ['./plateau.component.css']
})
export class PlateauComponent implements OnInit {
  plateau:Plateau = {
    idPartie: '',
    etatPartie: '',
    lesJoueurs: [],
    lesVilles: [],
    carteEpidemie: [],
    cartesPropagation: [],
    defausse_cartesJoueur: [],
    defausse_carteDePropagation: [],
    cartesJoueur: [],
    nomJoueur: '',
    cartes_en_main: []
  };
  joueurService: JoueurService
  pollingInterval: any
  images?:Images;
  carteEnMainVisible:boolean=false;
  imageUrls: { key: string, url: string }[] = [];


  constructor(private route: ActivatedRoute,private serviceJoueur:JoueurService,private router:Router, private imageService:ImageService) {
    this.joueurService = serviceJoueur
  }

  ngOnInit(): void {
   this.plateau = history.state.plateau;
   this.images = history.state.images;
   this.imageService.setImageData(history.state.images);

    this.refreshPlateau();
    this.pollingInterval = setInterval(() => {
      this.refreshPlateau();
    }, 10000)
  }

  getPosition(event: MouseEvent) {
    const bg = document.getElementById('bg');
    if(bg){
      const bgWidth = bg.offsetWidth;
      const bgHeight = bg.offsetHeight;
      const x = Math.round((event.clientX / bgWidth) * 100);
      const y = Math.round(((bgHeight - event.clientY) / bgHeight) * 100);
      console.log(`Position: (${x}%, ${y}%)`);
    }
  }


  envoyerTitre(titre: string) {
    // Faire quelque chose avec la valeur du titre, comme l'envoyer à un service
    console.log(`Le titre ${titre} a été cliqué !`);
  }

  piocher(titre: string){
    let body = {
      idPartie:this.plateau.idPartie,
      nomJoueur:this.plateau.nomJoueur
    }
    this.serviceJoueur.piocherCarte(body).subscribe(data=>{
      this.plateau = data
    });

  }

  refreshPlateau(){
    if(this.plateau){
      let body = {
        idPartie: this.plateau.idPartie
      }
      this.joueurService.actualiserPlateau(body).subscribe((data) => {
        this.plateau = data;
      })
    }
    console.log(this.plateau)
  }

  afficherCarteEnMain(){
    this.imageUrls = this.imageService.getImageUrls(this.plateau.cartes_en_main);
    this.carteEnMainVisible=true;
    console.log(this.imageUrls)
  }
  
  
  ngOnDestroy(){
    clearInterval(this.pollingInterval);
  }
}
