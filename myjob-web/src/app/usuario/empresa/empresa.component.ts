import { Component, OnInit } from '@angular/core';
import {Location} from '@angular/common';
import {Router} from '@angular/router';
import {UsuarioService} from '../usuario.service';
import {EmpresaDto} from '../../../model/empresa-dto';

@Component({
  selector: 'app-empresa',
  templateUrl: './empresa.component.html',
  styleUrls: ['./empresa.component.css']
})
export class EmpresaComponent implements OnInit {

  constructor(
    private empresaService: UsuarioService,
    private location: Location,
    private router: Router
  ) {}

  displayedColumns: string[] = ['idEmp', 'razaoSocial', 'nomeFant', 'cnpj', 'acoes'];

  empresa: EmpresaDto[];
  dataSource;

  ngOnInit(): void {
    this.empresaService.listarEmpresas().subscribe(dados => {
        this.empresa = dados;
        this.dataSource = this.empresa;
      });
    }
    editarEmpresa(empresa: EmpresaDto): void {
      this.router.navigate(['/empresa-detalhe', empresa.idEmp]);
    }
    cadastrar(): void {
      this.router.navigate(['/empresa-detalhe']);
    }
  deletarEmpresa(empresa: EmpresaDto): void {
    this.empresaService.deletarEmpresa(empresa.idEmp).subscribe(dados => {
      this.router.navigate(['/empresa-detalhe'])
        .then(() => {
          window.location.reload();
        });
    });
  }
  }



