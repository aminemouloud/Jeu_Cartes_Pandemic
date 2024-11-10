import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AccueilComponent } from './accueil/accueil.component';
import { LoginComponent } from './login/login.component';
import { MenuComponent } from './menu/menu.component';

import { CreationPartieComponent } from './creation-partie/creation-partie.component';
import { PartiesSuspenduesComponent } from './parties-suspendues/parties-suspendues.component';
import { CompteComponent } from './compte/compte.component';
import { PartiesComponent } from './parties/parties.component';
import { PlateauComponent } from './plateau/plateau.component';

const routes: Routes = [
  { path: 'login', component: LoginComponent},
  {path:'accueil', component:AccueilComponent},
  {path:'menu', component:MenuComponent},
  {path:'creerPartie', component:CreationPartieComponent},
  {path:'partieSuspendues', component:PartiesSuspenduesComponent},
  {path:'creerCompte', component:CompteComponent},
  {path:'parties', component:PartiesComponent},
  {path:'plateau', component:PlateauComponent},
  { path: '**', redirectTo: 'login' }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
