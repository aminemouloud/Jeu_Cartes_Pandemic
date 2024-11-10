import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { LoginComponent } from './login/login.component';
import { FormsModule } from '@angular/forms';
import { HttpClientModule, HTTP_INTERCEPTORS } from '@angular/common/http';
import { JwtTokenInterceptor } from './interceptors/jwt.token.interceptor';
import { AccueilComponent } from './accueil/accueil.component';
import { MenuComponent } from './menu/menu.component';
import { CreationPartieComponent } from './creation-partie/creation-partie.component';
import { PartiesSuspenduesComponent } from './parties-suspendues/parties-suspendues.component';
import { CompteComponent } from './compte/compte.component';
import { PartiesComponent } from './parties/parties.component';
import { PlateauComponent } from './plateau/plateau.component';

@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    AccueilComponent,
    MenuComponent,
    CreationPartieComponent,
    PartiesSuspenduesComponent,
    CompteComponent,
    PartiesComponent,
    PlateauComponent,
  
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    HttpClientModule,
    FormsModule
  ],
  providers: [{
    provide: HTTP_INTERCEPTORS,
    useClass: JwtTokenInterceptor,
    multi: true

  }
   
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
