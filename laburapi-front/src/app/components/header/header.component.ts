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

  nombreSession = '';

  constructor(
    private sessionService: SessionService,
    private router: Router
    ) {}

  ngOnInit(): void {this.getPersonaNombre();}

  getPersonaNombre(){
    if(this.sessionService.isAuth()){
      this.nombreSession = this.sessionService.getSessionData().nombre;
    }
  }

  logout(){
    this.sessionService.sessionLogout();
    this.router.navigateByUrl('/laburapi/login')
  }
}
