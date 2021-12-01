import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { IPresencia } from '../models/Presencia';

@Injectable({
  providedIn: 'root'
})

export class PresenciaService {

  api_url: string = 'http://localhost:8080/laburapi';

  constructor(private http: HttpClient) {}

  createPresencia(presencia: IPresencia){
    console.log('createPresencia----------- ');
    return this.http.post<IPresencia>(this.api_url+'/presencia',presencia);
  }

  patchPresencia(id:string, payload:any){
    console.log('patchPresencia----------- ');
    return this.http.patch<IPresencia>(this.api_url+'/presencia/'+id,payload);
  }

  getPresenciasByPersonaId(id:string){
    console.log('getPresenciasByPersonaId---------------');
    return this.http.get<IPresencia[]>(this.api_url+'/presencias/'+id)
  }

}
