import { Injectable } from '@angular/core';
import { HttpRequest, HttpHandler, HttpEvent, HttpInterceptor, HttpErrorResponse } from '@angular/common/http';
import { Observable, throwError } from 'rxjs';
import { catchError } from 'rxjs/operators';
import { Router } from '@angular/router';
import { SessionService } from '../services/session.service';

@Injectable()
export class UnAuthInterceptor implements HttpInterceptor {

  constructor(private sessionService: SessionService, private router: Router) {}

  intercept(request: HttpRequest<unknown>, next: HttpHandler): Observable<HttpEvent<unknown>> {
    return next.handle(request).pipe(
      catchError((error: HttpErrorResponse) => {
      if(error.status === 401){
        this.sessionService.destroyDataSession();
        console.log('-----------En el intercept ------------ destroyDataSession');
        this.router.navigateByUrl('/laburapi/login');
      }
      console.log('-----------En el intercept ------------ antes del return throwError(error)');
      return throwError(error);
    })
    );
  }
}
