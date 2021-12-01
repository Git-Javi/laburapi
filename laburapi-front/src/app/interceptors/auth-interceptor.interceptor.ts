import { HttpEvent, HttpHandler, HttpHeaders, HttpInterceptor, HttpRequest } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { SessionService } from '../services/session.service';

@Injectable({
  providedIn: 'root'
})
export class AuthInterceptor implements HttpInterceptor {

  constructor(private sessionService: SessionService) { }

  intercept(req: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
    let sessionId = this.sessionService.getSessionId();
    const headers = new HttpHeaders({ Authorization: 'Basic ' + btoa('user:'+sessionId) });
    console.log('AuthInterceptorService----idSession---->'+sessionId);
    if(sessionId){
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
