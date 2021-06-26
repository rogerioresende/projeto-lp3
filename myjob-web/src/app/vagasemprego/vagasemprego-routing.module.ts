import { NgModule } from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {VagasempresasComponent} from './vagasempresas/vagasempresas.component';
import {VagasDetalheComponent} from './vagas-detalhe/vagas-detalhe.component';
import {VagasusuarioComponent} from './vagasusuario/vagasusuario.component';



const vagasEmpregoRoutes: Routes = [
  {path: 'vagasemprego', component: VagasDetalheComponent},
  {path: 'vagasemprego/:idVaga', component: VagasDetalheComponent},
  {path: 'vagasempregousuario', component: VagasusuarioComponent},
  {path: 'vagasempregoempresa', component: VagasempresasComponent}
];

@NgModule({
  imports: [RouterModule.forChild(vagasEmpregoRoutes)],
  exports: [RouterModule]
})
export class VagasempregoRoutingModule { }

