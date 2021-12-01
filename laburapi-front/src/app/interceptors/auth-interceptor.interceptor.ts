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
    if(sessionId){
      let authRequest = req.clone({
        headers
      });
      return next.handle(authRequest);
    }
    return next.handle(req);
  }
}
