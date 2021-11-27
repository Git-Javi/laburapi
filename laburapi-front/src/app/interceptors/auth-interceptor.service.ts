import { HttpEvent, HttpHandler, HttpHeaders, HttpInterceptor, HttpRequest } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { PersonaService } from '../services/persona.service';

@Injectable({
  providedIn: 'root'
})
export class AuthInterceptorService implements HttpInterceptor {

  constructor(private personaService: PersonaService) { }

  intercept(req: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
    const headers = new HttpHeaders({ Authorization: 'Basic ' + btoa('user:'+this.personaService.getPersonaSessionId()) });
    let idSession = this.personaService.getPersonaSessionId();
    console.log('AuthInterceptorService----idSession---->'+idSession);
    if(idSession){
      let authRequest = req.clone({
        //headers: req.headers.set('Authorization: ','Basic '+btoa('user:'+this.personaService.getPersonaSessionId()) )
        headers
      });
      return next.handle(authRequest);
    }
    console.log('Ha paso por el interceptor sin añadir el auth --->' +next.handle(req));
    return next.handle(req);
  }
}
