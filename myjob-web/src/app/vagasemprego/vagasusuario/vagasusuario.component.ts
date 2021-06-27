import { Component, OnInit } from '@angular/core';
import {Router} from '@angular/router';
import {VagasDto} from '../../../model/vagas-dto';
import {VagasempregoService} from '../vagasemprego.service';
import {UsuarioService} from '../../usuario/usuario.service';

@Component({
  selector: 'app-vagasusuario',
  templateUrl: './vagasusuario.component.html',
  styleUrls: ['./vagasusuario.component.css']
})
export class VagasusuarioComponent implements OnInit {

  constructor(
    private vagasempregoService: VagasempregoService,
    private usuarioService: UsuarioService,
    private router: Router
  ) { }

  displayedColumns: string[] = ['idVaga', 'nomeEmp', 'nivelEsco', 'nivelTec', 'espVaga', 'acoes'];

  vagas: VagasDto[];

  dataSource;

  ngOnInit(): void {
    this.usuarioService.listarEmpresasPorUsuario().subscribe(obj => {
      if (obj !== null){
        this.router.navigate(['/vagasempregoempresa']);
      }
      this.vagasempregoService.listarVagasPessoaFisica().subscribe(dados => {
        this.vagas = dados;
        this.dataSource = this.vagas;
      });
    });
  }
}
