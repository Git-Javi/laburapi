import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { IPersona } from '../models/Persona';

@Injectable({
  providedIn: 'root'
})
export class PersonaService {

  api_url: string = 'http://localhost:8080/laburapi';

  constructor(private http: HttpClient) {}

  getPersona(id: string){
    return this.http.get<IPersona>(this.api_url+'/persona/'+id);
  }

  getPersonas(){
    return this.http.get<IPersona[]>(this.api_url+'/personas')
  }
}
