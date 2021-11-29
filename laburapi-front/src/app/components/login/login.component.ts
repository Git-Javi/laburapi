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

  constructor(private sessionService: SessionService, private router: Router) { }

  ngOnInit(): void {}

  getLogin(){
    this.sessionService.getSessionLogin(this.id).subscribe(
      (data)=>{
        sessionStorage.setItem('id',data.id.toString());
        console.log('holi----'+JSON.stringify(data));
        //this.router.navigate(/registro);
        console.log('sessionStorage----------->'+sessionStorage.getItem('id'))
        this.router.navigateByUrl('/laburapi/registro')
      },(error)=>{
        console.log('No hay nadie registrado con ese ID');
        this.msg = 'El ID introducido es incorrecto';
      }
    )
  }
}
