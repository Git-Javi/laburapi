import { Injectable } from '@angular/core';
import { HttpRequest, HttpHandler, HttpEvent, HttpInterceptor, HttpErrorResponse } from '@angular/common/http';
import { Observable, throwError } from 'rxjs';
import { catchError } from 'rxjs/operators';
import { Router } from '@angular/router';
import { PersonaService } from '../services/persona.service';

@Injectable()
export class UnAuthInterceptor implements HttpInterceptor {

  constructor(private personaService: PersonaService, private router: Router) {}

  intercept(request: HttpRequest<unknown>, next: HttpHandler): Observable<HttpEvent<unknown>> {
    return next.handle(request).pipe(
      catchError((error: HttpErrorResponse) => {
      if(error.status === 401){
        this.personaService.destroyPersonaSession();
        this.router.navigateByUrl('/laburapi/login');
      }
      return throwError(error);
    })
    );
  }
}
