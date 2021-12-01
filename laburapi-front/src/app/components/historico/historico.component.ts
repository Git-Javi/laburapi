import { Component, OnInit } from '@angular/core';
import { IPresencia } from 'src/app/models/Presencia';
import { PresenciaService } from 'src/app/services/presencia.service';
import { SessionService } from 'src/app/services/session.service';

@Component({
  selector: 'app-historico',
  templateUrl: './historico.component.html',
  styleUrls: ['./historico.component.css']
})
export class HistoricoComponent implements OnInit {

  presencias:IPresencia[] = [];

  constructor(
    private sessionService: SessionService,
    private presenciaService: PresenciaService
  ) { }

  ngOnInit(): void {
    this.getPresenciasByPersonaId();
  }

  getPresenciasByPersonaId(){
    let id = this.sessionService.getSessionId();
    this.presenciaService.getPresenciasByPersonaId(id).subscribe(
      (data=>{
        this.presencias = data;
      })
    );
  }

  getHours(inicio:Date,fin: any){
    return (new Date(inicio).getHours() - new Date(fin).getHours());
  }

}
