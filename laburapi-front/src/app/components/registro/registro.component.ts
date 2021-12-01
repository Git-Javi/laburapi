import { Component, OnInit} from '@angular/core';
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
  
  idTipoPresencia: number = 1;
  nombreTipoPresencia: string = '';
  tiposPresencia: ITipoPresencia[] = [];

  idPresenciaRegistrada:number = 0;
  textoBtn:string = '';

  constructor(
    private sessionService: SessionService,
    private presenciaService: PresenciaService,
    private tipoPresenciaService: TipoPresenciaService,
  ) { }

  ngOnInit(): void {
    console.log('ngOnInit----------------------------------------------');
    this.getTiposPresencia();
    //this.toggleIncioPresencia();
    this.toggleTextoBtn();
    this.toggleBtn();
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
        //this.idPresenciaRegistrada = data.id || 0;
        this.sessionService.setSessionPresenciaData(data);
        console.log('RegistroComponent-----------savePresencia----------=>'+JSON.stringify(data));
        console.log('RegistroComponent-----------savePresencia------EN LA SESION----=>'+JSON.stringify(this.sessionService.getSessionPresenciaData()));
      } 
    );
  }

  updatePresencia(){
    let fin = { fin : new Date(Date.now()) };
    let id = this.sessionService.getSessionPresenciaData().id || 0;
    console.log('########################################'+JSON.stringify(fin));
    this.presenciaService.patchPresencia(id,fin).subscribe(
      (data)=>{
        console.log('RegistroComponent-----------updatePresencia----------=>'+JSON.stringify(data));
      } 
    );
    this.sessionService.removeSessionPresenciaData();
  }

  createPresencia():Presencia{
    return  new Presencia(
      null,
      new Date(Date.now()),
      null,
      this.sessionService.getSessionPersonaData(),
      new TipoPresencia(this.idTipoPresencia,this.getTextoTipoPresencia())
    );
  }

  toggleRegistroPresencia(){
    const btnClassList: any = document.getElementById('btn')?.classList;
    console.log('toggleRegistroPresencia-----------this.registrado');
    if(this.sessionService.isRegistering()){
      console.log('toggleRegistroPresencia-----------this.registrado');
      this.updatePresencia();
      btnClassList.remove('btn-secondary');
      btnClassList.remove('text-warning'); 
      btnClassList.add('btn-warning');
      btnClassList.add('text-dark');
      this.textoBtn = 'Inciar';
    } else {
      console.log('toggleRegistroPresencia-----------this.Not!registrado');
      this.savePresencia();
      btnClassList.remove('btn-warning');
      btnClassList.remove('text-dark'); 
      btnClassList.add('btn-secondary');
      btnClassList.add('text-warning');
      this.textoBtn = 'Finalizar';
    }
  }

  toggleTextoBtn(){
    if(this.sessionService.isRegistering()){
      this.textoBtn = 'Finalizar';
    } else {
      this.textoBtn = 'Inciar';
    }
  }

  getTextoTipoPresencia(){
    let texto = this.tiposPresencia.find((s)=>s.id == this.idTipoPresencia)?.nombre; 
    if(texto){ return texto; } else { return ''; }
  }

  toggleBtn() {
    const btnClassList: any = document.getElementById('btn')?.classList;
    const selectElement: any = document.getElementById("select");
    console.log('????????#####El valor del registrado#####??????????? '+this.sessionService.isRegistering());
    if(this.sessionService.isRegistering()){
      console.log('????????SI está registrando???????????'+btnClassList);
      btnClassList.remove('btn-warning');
      btnClassList.remove('text-dark'); 
      btnClassList.add('btn-secondary');
      btnClassList.add('text-warning');
      selectElement.disabled = true;
    } else {
      console.log('????????No está registrando???????????'+btnClassList);
      btnClassList.remove('btn-secondary');
      btnClassList.remove('text-warning'); 
      btnClassList.add('btn-warning');
      btnClassList.add('text-dark');
      selectElement.disabled = false;
    }
    this.toggleTextoBtn();
  }
}
