import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { VagasusuarioComponent } from './vagasusuario/vagasusuario.component';
import {VagasDetalheComponent} from './vagas-detalhe/vagas-detalhe.component';
import {VagasempresasComponent} from './vagasempresas/vagasempresas.component';
import {VagasempregoRoutingModule} from './vagasemprego-routing.module';
import {MatTableModule} from '@angular/material/table';



@NgModule({
  declarations: [
    VagasusuarioComponent,
    VagasDetalheComponent,
    VagasempresasComponent
  ],
  imports: [
    CommonModule,
    VagasempregoRoutingModule,
    MatTableModule
  ]
})
export class VagasempregoModule { }
