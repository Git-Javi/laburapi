import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { IPersona } from '../models/Persona';

@Injectable({
  providedIn: 'root'
})
export class PersonaService {

  api_url: string = 'http://localhost:8080/laburapi';

  constructor(private http: HttpClient) {
    console.log("-- servicio funcionando --")
  }

  setPersonaSessionId(id: string): void {
    sessionStorage.setItem('id',id);
  }

   
  getPersonaSessionId(): string {
     return sessionStorage.getItem('id') || '';
  }

  destroyPersonaSession(): void {
    sessionStorage.clear();
  }

  isAuth(): boolean {
    if(sessionStorage.getItem('id')){
      return true;
    } else {
      return false;
    }
  }

  getPersona(id: string){
    console.log('getPersona----------- el id de la autorización -----'+id);
    return this.http.get<IPersona>(this.api_url+'/persona/'+id);
  }

  getPersonas(){
    console.log('getPersonas----------- No Id -----');
    //const headers = new HttpHeaders({ Authorization: 'Basic ' + btoa('user:'+sessionStorage.getItem('id')) });
    //return this.http.get<IPersona[]>(this.api_url+'/personas',{headers})
    return this.http.get<IPersona[]>(this.api_url+'/personas')
  }
}
