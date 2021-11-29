import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { SessionService } from 'src/app/services/session.service';
import { PersonaService } from 'src/app/services/persona.service';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent implements OnInit {

  nombre = '';

  constructor(
    private sessionService: SessionService,
    private personaService: PersonaService,
    private router: Router
    ) { }

  ngOnInit(): void {this.getPersonaNombre();}

  getPersonaNombre(){
    let id = this.personaService.getPersonaSessionId();
    this.personaService.getPersona(id).subscribe(
      (data)=>{
        this.nombre = data.nombre;
      });
  }

  logout(){
    this.sessionService.sessionLogout();
    this.router.navigateByUrl('/laburapi/login')
  }
}
