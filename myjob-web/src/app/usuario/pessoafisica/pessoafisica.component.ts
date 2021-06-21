import { Component, OnInit } from '@angular/core';
import {UsuarioService} from '../usuario.service';
import {Location} from '@angular/common';
import {Router} from '@angular/router';
import {PessoafisicaDto} from '../../../model/pessoafisica-dto';

@Component({
  selector: 'app-pessoafisica',
  templateUrl: './pessoafisica.component.html',
  styleUrls: ['./pessoafisica.component.css']
})
export class PessoafisicaComponent implements OnInit {

  constructor(
    private pessoafisicaService: UsuarioService,
    private location: Location,
    private router: Router
  ) {
  }

  displayedColumns: string[] = ['idPess', 'nome', 'cpf', 'sexo', 'idade', 'acoes'];

  pessoafisica: PessoafisicaDto[];

  dataSource;

  ngOnInit(): void {
    this.pessoafisicaService.listarPessoa().subscribe(dados => {
      this.pessoafisica = dados;
      this.dataSource = this.pessoafisica;
    });
  }

  editarPessoaFisica(pessoafisica: PessoafisicaDto): void {
    this.router.navigate(['/pessoafisica-detalhe', pessoafisica.idPess]);
  }

  cadastrar(): void {
    this.router.navigate(['/pessoafisica-detalhe']);
  }
}
