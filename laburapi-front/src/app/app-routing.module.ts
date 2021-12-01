import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { HistoricoComponent } from './components/historico/historico.component';
import { LoginComponent } from './components/login/login.component';
import { RegistroComponent } from './components/registro/registro.component';


const routes: Routes = [
  { path: '',  redirectTo: 'login', pathMatch: 'full' },
  { path: 'login', component:LoginComponent, pathMatch: 'full' },
  { path: 'registro', component:RegistroComponent, pathMatch: 'full' },
  { path: 'historico', component:HistoricoComponent, pathMatch: 'full' },
  //{ path: '**', component:NotFoundComponent, pathMatch: 'full' } // Si introducen alguna ruta errónea
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})

export class AppRoutingModule { }