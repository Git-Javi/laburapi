import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Presencia } from 'src/app/models/Presencia';
import { ITipoPresencia, TipoPresencia } from 'src/app/models/TipoPresencia';
import { PresenciaService } from 'src/app/services/presencia.service';
import { SessionService } from 'src/app/services/session.service';
import { TipoPresenciaService } from 'src/app/services/tipoPresencia.service';

@Component({
  selector: 'app-registro',
  templateUrl: './registro.component.html',
  styleUrls: ['./registro.component.css']
})

export class RegistroComponent implements OnInit {
  
  tiempoEfectivo: string = '';
  idTipoPresencia: number = 1;
  nombreTipoPresencia: string = '';
  tiposPresencia: ITipoPresencia[] = [];
  incioPresencia:boolean = false;
  idPresenciaRegistrada:number = 0;
  textoBtn:string = '';

  constructor(
    private sessionService: SessionService,
    private presenciaService: PresenciaService,
    private tipoPresenciaService: TipoPresenciaService,
    private router: Router
  ) { }

  ngOnInit(): void {
    this.getTiposPresencia();
    this.toggleIncioPresencia();
    this.toggleTextoBtn();
  }

  getTiposPresencia(){
    this.tipoPresenciaService.getTiposPresencia().subscribe(
      (data)=>{
        this.tiposPresencia = data;
      } 
    );
  }

  savePresencia(){
    this.presenciaService.createPresencia(this.createPresencia()).subscribe(
      (data)=>{
        this.idPresenciaRegistrada = data.id || 0;
        console.log('RegistroComponent-----------savePresencia----------=>'+JSON.stringify(data));
      } 
    );
  }

  updatePresencia(){
    let fin = { fin : new Date(Date.now()) };
    console.log('########################################'+JSON.stringify(fin));
    this.presenciaService.patchPresencia(this.sessionService.getSessionId(),fin).subscribe(
      (data)=>{
        console.log('RegistroComponent-----------savePresencia----------=>'+JSON.stringify(data));
      } 
    );
  }

  createPresencia():Presencia{
    return  new Presencia(
      null,
      new Date(Date.now()),
      null,
      this.sessionService.getSessionData(),
      new TipoPresencia(this.idTipoPresencia,this.getTextoTipoPresencia())
    );
  }

  toggleRegistroPresencia(){
    console.log('toggleRegistroPresencia-----------this.registrado'+this.incioPresencia);
    if(this.incioPresencia){
      console.log('toggleRegistroPresencia-----------this.registrado');
      this.savePresencia();
    } else {
      console.log('toggleRegistroPresencia-----------this.Not!registrado');
      this.updatePresencia();
    }
    console.log('toggleRegistroPresencia-----------toggleTextoBtn');
    this.toggleIncioPresencia();
    this.toggleTextoBtn();
  }

  toggleTextoBtn(){
    if(this.incioPresencia){
      this.textoBtn = 'Iniciar';
    } else {
      this.textoBtn = 'Finalizar';
    }
  }

  toggleIncioPresencia(){
    if(this.incioPresencia){
      this.incioPresencia = false;
    } else {
      this.incioPresencia = true;
    }
  }

  getTextoTipoPresencia(){
    let texto = this.tiposPresencia.find((s)=>s.id == this.idTipoPresencia)?.nombre; 
    if(texto){ return texto; } else { return ''; }
  }

  toggleBtnClass(e:any) {
    const classList = e.target.classList;
    if(this.incioPresencia){
      classList.remove('btn-warning');
      classList.remove('text-dark'); 
      classList.add('btn-secondary');
      classList.add('text-warning');
    } else {
      classList.remove('btn-secondary');
      classList.remove('text-warning'); 
      classList.add('btn-warning');
      classList.add('text-dark');
    }
  }
}
