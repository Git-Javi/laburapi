import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { SessionService } from 'src/app/services/session.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  id: string ='';
  msg: string = '';

  constructor(
    private sessionService: SessionService,
    private router: Router
  ) { }

  ngOnInit(): void {}

  getLogin(){
    this.sessionService.getSessionLogin(this.id).subscribe(
      (data)=>{
        this.sessionService.setSessionPersonaData(data);
        this.router.navigateByUrl('registro')
      },(error)=>{
        this.msg = 'El ID introducido es incorrecto';
      }
    )
  }
}
