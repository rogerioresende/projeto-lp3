import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {MatSnackBar} from '@angular/material/snack-bar';
import {VagasDto} from '../../model/vagas-dto';
import {EMPTY, Observable} from 'rxjs';
import {environment} from '../../environments/environment';
import {catchError, map} from 'rxjs/operators';


@Injectable({
  providedIn: 'root'
})
export class VagasempregoService {

  constructor(
    private httpCliente: HttpClient,
    private snackbar: MatSnackBar
  ) {
  }

  vagas: VagasDto[];

  listarVagasEmpresas(): Observable<VagasDto[]> {
    const url = `${environment.config.URL_API}/vagasEmprego/`;
    const email = localStorage.getItem('email');
    return this.httpCliente.get<VagasDto[]>(url + email).pipe(
      map((vagas) => vagas)
    );
  }

  cadastrarVagasEmprego(vagas: VagasDto): Observable<VagasDto> {
    const url = `${environment.config.URL_API}/vagasEmprego/add`;
    vagas.usuario = {
      email: localStorage.getItem('email_acesso'),
      senha: localStorage.getItem('senha_acesso')
    };
    return this.httpCliente.post<VagasDto>(url, vagas).pipe(
      map(obj => obj),
      catchError((e) => this.errorHandler(e))
    );
  }
  editarVagasEmprego(vagas: VagasDto): Observable<VagasDto> {
    const url = `${environment.config.URL_API}/vagasEmprego/edit`;
    return this.httpCliente.put<VagasDto>(url, vagas).pipe(
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
  deletarVagaEmprego(idVaga: number): Observable<VagasDto> {
    const url = `${environment.config.URL_API}/vagasEmprego/delete/`;
    return this.httpCliente.delete<VagasDto>(url + idVaga).pipe(
      map((obj) => obj),
      catchError( (e) => this.errorHandler(e))
    );
  }
}
