import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { IPersona } from '../models/Persona';
import { PersonaService } from './persona.service';

@Injectable({
    providedIn: 'root'
})
export class SessionService {

    constructor(private http: HttpClient, private personaService: PersonaService) {}

    getSessionLogin(id: string) {
        /*//const headers = new HttpHeaders({ Authorization: 'Basic ' + btoa('user:'+id) });
        console.log('LoginService----id---->'+id);
        this.personaService.setPersonaSessionId(id);
        //return this.http.get<IPersona>(this.api_url+'/persona/'+id, { headers });
        return this.http.get<IPersona>(this.api_url+'/persona/'+id/*, { headers });*/
        console.log('LoginService----id---->'+id);
        this.personaService.setPersonaSessionId(id);
        return this.personaService.getPersona(id);
    }

    sessionLogout() {
        /*//const headers = new HttpHeaders({ Authorization: 'Basic ' + btoa('user:'+id) });
        console.log('LoginService----id---->'+id);
        this.personaService.setPersonaSessionId(id);
        //return this.http.get<IPersona>(this.api_url+'/persona/'+id, { headers });
        return this.http.get<IPersona>(this.api_url+'/persona/'+id/*, { headers });*/
        console.log('Se borra el id-->'+ this.personaService.getPersonaSessionId())
        this.personaService.destroyPersonaSession();
        console.log('Ya no hya id-->'+ this.personaService.getPersonaSessionId())
    }
}