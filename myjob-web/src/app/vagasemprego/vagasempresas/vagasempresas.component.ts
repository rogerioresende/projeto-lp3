import { Component, OnInit } from '@angular/core';
import {UsuarioService} from '../../usuario/usuario.service';
import {ActivatedRoute, Router} from '@angular/router';
import {VagasempregoService} from '../vagasemprego.service';
import {FormBuilder} from '@angular/forms';
import {VagasDto} from '../../../model/vagas-dto';


@Component({
  selector: 'app-vagas',
  templateUrl: './vagasempresas.component.html',
  styleUrls: ['./vagasempresas.component.css']
})
export class VagasempresasComponent implements OnInit {

  constructor(private vagasempregoService: VagasempregoService,
              private fb: FormBuilder,
              private router: Router,
              private route: ActivatedRoute,
              private usuarioService: UsuarioService,
  ) {
  }

  displayedColumns: string[] = ['idVaga', 'nomeEmp', 'nivelEsco', 'nivelTec', 'espVaga', 'acoes'];
  vagas: VagasDto[];
  dataSource;

  ngOnInit(): void {
    this.vagasempregoService.listarVagasEmpresas().subscribe(dados => {
      this.vagas = dados;
      this.dataSource = this.vagas;
    });
  }
  editarVagasEmprego(vagas: VagasDto): void {
    this.router.navigate(['/vagas-detalhe', vagas.idVaga]);
  }
  cadastrarVagasEmprego(): void {
    this.router.navigate(['/vagas-detalhe']);
  }
  deletarVagaEmprego(vagas: VagasDto): void {
    this.vagasempregoService.deletarVagaEmprego(vagas.idVaga).subscribe(dados => {
      this.router.navigate(['/vagas-detalhe'])
        .then(() => {
          window.location.reload();
        });
    });
  }
}
