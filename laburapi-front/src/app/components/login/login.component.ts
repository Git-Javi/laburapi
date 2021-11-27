import { Component, OnInit } from '@angular/core';
import { LoginService } from 'src/app/services/login.service';


@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  id: string ='';

  constructor(private loginService: LoginService) { }

  ngOnInit(): void {}

  getLogin(){
    this.loginService.getPersonaLogin(this.id).subscribe(
      (data)=>{
        sessionStorage.setItem('id',data.id.toString());
        console.log('holi----'+JSON.stringify(data));
        //this.router.navigate(/registro);
        console.log('sessionStorage----------->'+sessionStorage.getItem('id'))
      },(error)=>{
        console.log('No hay nadie registrado con ese ID');
      }
    )
  }

}
