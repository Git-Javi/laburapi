import { Injectable } from '@angular/core';
import { IPersona } from '../models/Persona';
import { IPresencia } from '../models/Presencia';
import { PersonaService } from './persona.service';

@Injectable({
    providedIn: 'root'
})

export class SessionService {

  constructor( private personaService: PersonaService ) {}

  setSessionId(id: string): void {
    sessionStorage.setItem('id',id);
  }
    
  getSessionId(): string {
      return sessionStorage.getItem('id') || '';
  }
    
  setSessionPersonaData(sessionPersonaData: IPersona): void {
    sessionStorage.setItem('persona',JSON.stringify(sessionPersonaData));
  }

  getSessionPersonaData(): IPersona {
    return JSON.parse(sessionStorage.getItem('persona') || '');
  }

  setSessionPresenciaData(sessionPresenciaData: IPresencia): void {
    sessionStorage.setItem('presencia',JSON.stringify(sessionPresenciaData));
  }

  getSessionPresenciaData(): IPresencia {
    return JSON.parse(sessionStorage.getItem('presencia') || '');
  }
    
  removeSessionPresenciaData(){
    sessionStorage.removeItem('presencia');
  }

  destroySession(): void {
    sessionStorage.clear();
  }

  isAuth(): boolean {
    if(sessionStorage.getItem('id')){
      return true;
    } else {
      return false;
    }
  }

  isRegistering(): boolean {
    if(sessionStorage.getItem('presencia')){
      return true;
    } else {
      return false;
    }
  }

  getSessionLogin(id: string) {
    this.setSessionId(id);
    return this.personaService.getPersona(id);
  }

  sessionLogout() {
    this.destroySession();
  }
}