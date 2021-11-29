import { Time } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { PresenciaService } from 'src/app/services/presencia.service';

@Component({
  selector: 'app-registro',
  templateUrl: './registro.component.html',
  styleUrls: ['./registro.component.css']
})
export class RegistroComponent implements OnInit {

  tiempoEfectivo: string = '';

  constructor(private presenciaService: PresenciaService, private router: Router ) { }

  ngOnInit(): void {}




}
