//import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { IPersona } from '../models/Persona';
import { PersonaService } from './persona.service';

@Injectable({
    providedIn: 'root'
})

export class SessionService {

  constructor(
    //private http: HttpClient,
    private personaService: PersonaService
  ) {}

  setSessionId(id: string): void {
    sessionStorage.setItem('id',id);
  }
    
  getSessionId(): string {
      return sessionStorage.getItem('id') || '';
  }
    
  setSessionData(sessionData: IPersona): void {
    sessionStorage.setItem('persona',JSON.stringify(sessionData));
  }

  getSessionData(): IPersona {
    return JSON.parse(sessionStorage.getItem('persona') || '');
  }
    
  destroyDataSession(): void {
    sessionStorage.clear();
  }

  isAuth(): boolean {
    if(sessionStorage.getItem('id')){
      return true;
    } else {
      return false;
    }
  }

  getSessionLogin(id: string) {
    console.log('LoginService----id---->'+id);
    this.setSessionId(id);
    return this.personaService.getPersona(id);
  }

  sessionLogout() {
    this.destroyDataSession();
    console.log('Ya no hya id--> sessionLogout')
  }
}