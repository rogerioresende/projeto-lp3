import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {MatSnackBar} from '@angular/material/snack-bar';
import {EMPTY, Observable} from 'rxjs';
import {environment} from '../../environments/environment';
import {catchError, map} from 'rxjs/operators';
import {EmpresaDto} from '../../model/empresa-dto';
import {PessoafisicaDto} from '../../model/pessoafisica-dto';



@Injectable({
  providedIn: 'root'
})
export class UsuarioService {

  constructor(
    private httpCliente: HttpClient,
    private snackbar: MatSnackBar
  ) {
  }

  empresa: EmpresaDto[];

  listarEmpresasPorUsuario(): Observable<EmpresaDto> {
    const url = `${environment.config.URL_API}/empresa/listar/`;
    const email = localStorage.getItem('email');
    return this.httpCliente.get<EmpresaDto>(url + email).pipe(
      map((empresa) => empresa)
    );
  }

  cadastrarEmpresa(empresa: EmpresaDto): Observable<EmpresaDto> {
    const url = `${environment.config.URL_API}/empresa/add`;
    empresa.usuario = {
      email: localStorage.getItem('email_acesso'),
      senha: localStorage.getItem('senha_acesso')
    };
    return this.httpCliente.post<EmpresaDto>(url, empresa).pipe(
      map(obj => obj),
      catchError((e) => this.errorHandler(e))
    );
  }

  editarEmpresa(empresa: EmpresaDto): Observable<EmpresaDto> {
    const url = `${environment.config.URL_API}/empresa/edit/`;
    const email = localStorage.getItem('email');
    return this.httpCliente.put<EmpresaDto>(url + email, empresa).pipe(
      map(obj => obj),
      catchError((e) => this.errorHandler(e))
    );
  }

  errorHandler(e: any): Observable<any> {
    this.showMessage('Ocorreu um erro!', true);
    return EMPTY;
  }

  showMessage(msg: string, isError: boolean = false): void {
    this.snackbar.open(msg, 'X', {
      duration: 3000,
      horizontalPosition: 'center',
      verticalPosition: 'bottom',
      panelClass: isError ? ['msg-error'] : ['msg-success'],
    });
  }

  bucarEmpresaPorId(idEmp: number): Observable<EmpresaDto> {
    const url = `${environment.config.URL_API}/empresa/`;
    return this.httpCliente.get<EmpresaDto>(url + idEmp).pipe(
      map((empresa) => empresa),
      catchError((e) => this.errorHandler(e))
    );
  }

  listarPessoaPorUsuario(): Observable<PessoafisicaDto> {
    const url = `${environment.config.URL_API}/pessoaFisica/listar/`;
    const email = localStorage.getItem('email');
    return this.httpCliente.get<PessoafisicaDto>(url + email).pipe(
      map((pessoafisica) => pessoafisica)
    );
  }

  cadastrarPessoaFisica(pessoafisica: PessoafisicaDto): Observable<PessoafisicaDto> {
    const url = `${environment.config.URL_API}/pessoaFisica/add`;
    pessoafisica.usuario = {
      email: localStorage.getItem('email_acesso'),
      senha: localStorage.getItem('senha_acesso')
    };
    return this.httpCliente.post<PessoafisicaDto>(url, pessoafisica).pipe(
      map(obj => obj),
      catchError((e) => this.errorHandler(e))
    );
  }

  editarPessoaFisica(pessoafisica: PessoafisicaDto): Observable<PessoafisicaDto> {
    const url = `${environment.config.URL_API}/pessoaFisica/edit/`;
    const email = localStorage.getItem('email');
    return this.httpCliente.put<PessoafisicaDto>(url + email, pessoafisica).pipe(
      map(obj => obj),
      catchError((e) => this.errorHandler(e))
    );
  }

  bucarPessoaFisicaPorId(idPess: number): Observable<PessoafisicaDto> {
    const url = `${environment.config.URL_API}/pessoaFisica/`;
    return this.httpCliente.get<PessoafisicaDto>(url + idPess).pipe(
      map((pessoafisica) => pessoafisica),
      catchError((e) => this.errorHandler(e))
    );
  }

  deletarEmpresa(idEmp: number): Observable<any> {
    const url = `${environment.config.URL_API}/empresa/delete/`;
    return this.httpCliente.delete<any>(url + idEmp).pipe(
      map((obj) => obj),
      catchError( (e) => this.errorHandler(e))
    );
  }

  deletarPessoaFisica(idPess: number): Observable<any> {
    const url = `${environment.config.URL_API}/pessoaFisica/delete/`;
    return this.httpCliente.delete<any>(url + idPess).pipe(
      map((obj) => obj),
      catchError( (e) => this.errorHandler(e))
    );
  }
}

