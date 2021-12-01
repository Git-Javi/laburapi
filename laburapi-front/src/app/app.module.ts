import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { HttpClientModule, HTTP_INTERCEPTORS } from '@angular/common/http';
import { FormsModule } from '@angular/forms';

/* ########################### COMPONENTES ########################### */
import { AppComponent } from './components/app-component/app.component';
import { HeaderComponent } from './components/header/header.component';
import { FooterComponent } from './components/footer/footer.component';
import { LoginComponent } from './components/login/login.component';
import { RegistroComponent } from './components/registro/registro.component';

/* ########################### ROUTER ########################### */
import { AppRoutingModule } from './app-routing.module';

/* ########################### INTERCEPTORES ########################### */
import { AuthInterceptor } from './interceptors/auth-interceptor.interceptor';
import { UnAuthInterceptor } from './interceptors/unAuth-interceptor.interceptor';
import { HistoricoComponent } from './components/historico/historico.component';

@NgModule({
  declarations: [
    AppComponent,
    HeaderComponent,
    FooterComponent,
    LoginComponent,
    RegistroComponent,
    HistoricoComponent
  ],
  imports: [
    BrowserModule,
    FormsModule,
    HttpClientModule,
    AppRoutingModule
  ],
  providers: [
    { provide: HTTP_INTERCEPTORS, useClass: AuthInterceptor, multi: true },
    { provide: HTTP_INTERCEPTORS, useClass: UnAuthInterceptor, multi: true }
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
