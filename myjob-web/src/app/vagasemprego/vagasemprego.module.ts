import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { VagasusuarioComponent } from './vagasusuario/vagasusuario.component';
import {VagasDetalheComponent} from './vagas-detalhe/vagas-detalhe.component';
import {VagasempresasComponent} from './vagasempresas/vagasempresas.component';
import {VagasempregoRoutingModule} from './vagasemprego-routing.module';
import {MatTableModule} from '@angular/material/table';
import {MatButtonModule} from '@angular/material/button';
import {MatFormFieldModule} from '@angular/material/form-field';
import {MatInputModule} from '@angular/material/input';
import {ReactiveFormsModule} from '@angular/forms';
import {MatDatepickerModule} from '@angular/material/datepicker';
import {MatSelectModule} from '@angular/material/select';



@NgModule({
  declarations: [
    VagasusuarioComponent,
    VagasDetalheComponent,
    VagasempresasComponent
  ],
  imports: [
    CommonModule,
    MatTableModule,
    MatButtonModule,
    ReactiveFormsModule,
    MatFormFieldModule,
    MatInputModule,
    MatDatepickerModule,
    MatSelectModule,
    VagasempregoRoutingModule,

  ]
})
export class VagasempregoModule { }
