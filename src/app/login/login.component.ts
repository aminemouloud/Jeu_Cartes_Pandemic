import { Component, OnInit } from '@angular/core';
import { AuthService } from '../auth/services/auth.service';
import { Credentials } from '../modele/credentials';
import { Router } from '@angular/router';
import { JoueurService } from '../services/joueur.services';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  credentials: Credentials = new Credentials('', '');

  constructor(private authService: AuthService, private router : Router,private joueurService:JoueurService) { 
    
  }

  ngOnInit(): void {
  }
  public login(): void {
    this.authService.login(this.credentials)
  
  }
  creerCompte(){
    this.router.navigate(['/creerCompte'])
  }

}



