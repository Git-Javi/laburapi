import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { LoginComponent } from './components/login/login.component';
import { RegistroComponent } from './components/registro/registro.component';


const routes: Routes = [
  { path: '',  redirectTo: 'laburapi/login', pathMatch: 'full' },
  { path: 'laburapi', redirectTo: 'laburapi/login', pathMatch: 'full' },
  { path: 'laburapi/login', component:LoginComponent, pathMatch: 'full' },
  { path: 'laburapi/registro', component:RegistroComponent, pathMatch: 'full' },
  //{ path: 'contact', component:ContactComponent },
  //{ path: '**', component:NotFoundComponent, pathMatch: 'full' } // Si introducen alguna ruta errónea
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})

export class AppRoutingModule { }