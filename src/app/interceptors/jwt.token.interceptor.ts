
import { Injectable } from "@angular/core";
import { AuthService } from '../auth/services/auth.service';
import {HttpHandler, HttpRequest, HttpEvent, HttpInterceptor, HttpErrorResponse} from '@angular/common/http';
import {Observable, of} from 'rxjs';


@Injectable()
export class JwtTokenInterceptor implements HttpInterceptor {
 
  constructor(public auth: AuthService) {}
 
  intercept(request: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
    let interceptedRequest = request.clone({
      setHeaders: {
        Authorization: ` ${this.auth.getToken()}`
      }
    });
 
    return next.handle(interceptedRequest);
  }
}
