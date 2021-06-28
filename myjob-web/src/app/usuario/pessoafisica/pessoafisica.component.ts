import { Component, OnInit } from '@angular/core';
import {UsuarioService} from '../usuario.service';
import {Location} from '@angular/common';
import {Router} from '@angular/router';
import {PessoafisicaDto} from '../../../model/pessoafisica-dto';
import {AuthGuardService} from '../../guards/auth.guard.service';

@Component({
  selector: 'app-pessoafisica',
  templateUrl: './pessoafisica.component.html',
  styleUrls: ['./pessoafisica.component.css']
})
export class PessoafisicaComponent implements OnInit {

  constructor(
    private pessoafisicaService: UsuarioService,
    private location: Location,
    private router: Router,
    private auth: AuthGuardService
  ) {
  }

  displayedColumns: string[] = ['idPess', 'nome', 'cpf', 'sexo', 'idade', 'grauEsco', 'formaAcade', 'acoes'];

  pessoafisica: PessoafisicaDto[];

  dataSource;

  ngOnInit(): void {
    this.pessoafisicaService.listarPessoaPorUsuario().subscribe(dados => {
      this.pessoafisica = [dados];
      this.dataSource = this.pessoafisica;
      if (dados === null){
        this.router.navigate(['/usuario-empresa']);
      }
    });
  }

  editarPessoaFisica(pessoafisica: PessoafisicaDto): void {
    this.router.navigate(['/pessoafisica-detalhe', pessoafisica.idPess]);
  }

  deletarPessoaFisica(pessoaFisica: PessoafisicaDto): void {
    this.pessoafisicaService.deletarPessoaFisica(pessoaFisica.idPess).subscribe(dados => {
      this.auth.logout();
    });
  }
}
