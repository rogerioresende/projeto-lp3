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
  ) { }

  empresa: EmpresaDto[];
  pessoafisica: PessoafisicaDto[];

  listarEmpresas(): Observable<EmpresaDto[]> {
    const url = `${environment.config.URL_API}/empresa/`;
    return this.httpCliente.get<EmpresaDto[]>(url).pipe(
      map((empresa) => empresa)
    );
  }

  cadastrarEmpresa(empresa: EmpresaDto): Observable<EmpresaDto> {
    const url = `${environment.config.URL_API}/empresa/add`;
    empresa.usuario.email = localStorage.getItem('email');
    empresa.usuario.senha = localStorage.getItem('senha');
    return this.httpCliente.post<EmpresaDto>(url, empresa).pipe(
      map(obj => obj),
      catchError((e) => this.errorHandler(e))
    );
  }

  editarEmpresa(empresa: EmpresaDto): Observable<EmpresaDto> {
    const url = `${environment.config.URL_API}/empresa/edit`;
    return this.httpCliente.put<EmpresaDto>(url, empresa).pipe(
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
  listarPessoa(): Observable<PessoafisicaDto[]> {
  const url = `${environment.config.URL_API}/pessoaFisica/`;
  return this.httpCliente.get<PessoafisicaDto[]>(url).pipe(
    map((pessoafisica) => pessoafisica)
  );
}

  cadastrarPessoaFisica(pessoafisica: PessoafisicaDto): Observable<PessoafisicaDto> {
  const url = `${environment.config.URL_API}/pessoaFisica/add`;
  pessoafisica.usuario.email = localStorage.getItem('email');
  pessoafisica.usuario.senha = localStorage.getItem('senha');
  return this.httpCliente.post<PessoafisicaDto>(url, pessoafisica).pipe(
    map(obj => obj),
    catchError((e) => this.errorHandler(e))
  );
}

  editarPessoaFisica(pessoafisica: PessoafisicaDto): Observable<PessoafisicaDto> {
  const url = `${environment.config.URL_API}/pessoaFisica/edit`;
  return this.httpCliente.put<PessoafisicaDto>(url, pessoafisica).pipe(
    map(obj => obj),
    catchError((e) => this.errorHandler(e))
  );
}
  bucarTipoPorId(idPess: number): Observable<PessoafisicaDto> {
  const url = `${environment.config.URL_API}/pessoaFisica/`;
  return this.httpCliente.get<PessoafisicaDto>(url + idPess).pipe(
    map((empresa) => this.pessoafisica),
    catchError((e) => this.errorHandler(e))
  );
}
}
