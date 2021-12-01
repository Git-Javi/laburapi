import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { ITipoPresencia } from '../models/TipoPresencia';

@Injectable({
  providedIn: 'root'
})
export class TipoPresenciaService {

    api_url: string = 'http://localhost:8080/laburapi';

    constructor( private http: HttpClient ) { }

    getTiposPresencia(){
        return this.http.get<ITipoPresencia[]>(this.api_url+'/tipoPresencias')
    }
}