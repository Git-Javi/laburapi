import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { LoginService } from 'src/app/services/login.service';


@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  id: string ='';
  msg: string = '';

  constructor(private loginService: LoginService, private router: Router) { }

  ngOnInit(): void {}

  getLogin(){
    this.loginService.getPersonaLogin(this.id).subscribe(
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
