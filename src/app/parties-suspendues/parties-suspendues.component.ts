import { JoueurService } from './../services/joueur.services';
import { Component, OnInit } from '@angular/core';
import { Partie } from 'src/app/modele/partie';

@Component({
  selector: 'app-parties-suspendues',
  templateUrl: './parties-suspendues.component.html',
  styleUrls: ['./parties-suspendues.component.css']
})
export class PartiesSuspenduesComponent implements OnInit {
  parties:Partie[]=[];
  constructor(private JoueurService:JoueurService) { }

  ngOnInit(): void {
   this.parties = JSON.parse(localStorage.getItem("partiesSuspendues") || "");
   console.log(this.parties)

  }

 
}
