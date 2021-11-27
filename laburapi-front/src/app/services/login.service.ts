import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { IPersona } from '../models/Persona';
import { PersonaService } from './persona.service';

@Injectable({
    providedIn: 'root'
})
export class LoginService {

    constructor(private http: HttpClient, private personaService: PersonaService) {}

    getPersonaLogin(id: string) {
        /*//const headers = new HttpHeaders({ Authorization: 'Basic ' + btoa('user:'+id) });
        console.log('LoginService----id---->'+id);
        this.personaService.setPersonaSessionId(id);
        //return this.http.get<IPersona>(this.api_url+'/persona/'+id, { headers });
        return this.http.get<IPersona>(this.api_url+'/persona/'+id/*, { headers });*/
        console.log('LoginService----id---->'+id);
        this.personaService.setPersonaSessionId(id);
        return this.personaService.getPersona(id);
    }
}