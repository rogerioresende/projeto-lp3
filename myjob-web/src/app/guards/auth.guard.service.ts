import { Injectable } from '@angular/core';
import {BehaviorSubject, EMPTY, Observable} from 'rxjs';
import {Router} from '@angular/router';
import {HttpClient} from '@angular/common/http';
import {UsuarioDao} from '../../model/usuario-dao';
import {catchError, map} from 'rxjs/operators';
import {environment} from '../../environments/environment';
import {Usuario} from '../../model/usuario';
import {MatSnackBar} from '@angular/material/snack-bar';

@Injectable({
  providedIn: 'root'
})
export class AuthGuardService {
  private loggedIn = new BehaviorSubject<boolean>(false);

  // tslint:disable-next-line:typedef
  get isLoggedIn() {
    return this.loggedIn.asObservable();
  }

  constructor(
    private router: Router,
    private httpClient: HttpClient,
    private snackbar: MatSnackBar
  ) { }

  login (usuarioDao: UsuarioDao, primeiroAcesso: boolean) {
    if (primeiroAcesso === false) {
      if (usuarioDao.email !== '' && usuarioDao.senha !== null) {
        this.authenticate(usuarioDao).subscribe((dado) => {
          debugger;
          localStorage.setItem('email', usuarioDao.email);
          localStorage.setItem('usuario_id', dado.id);
          this.showMessage('Logado com sucesso', false);
          this.loggedIn.next(true);
          this.router.navigate(['/']);
        });
      }
    } else {
      if (usuarioDao.email !== '' && usuarioDao.senha !== null) {
        this.authenticate(usuarioDao).subscribe((dado) => {
          localStorage.setItem('email', usuarioDao.email);
          localStorage.setItem('usuario_id', dado.id);
          this.showMessage('Logado com sucesso', false);
          this.loggedIn.next(true);
          this.router.navigate(['/home']);
        });
      }
    }
  }

  logout (){
    this.loggedIn.next(false);
    localStorage.clear();
    this.router.navigate(['/login'])
      .then(() => {
        window.location.reload();
      });
  }

  authenticate(usuario: UsuarioDao): Observable<any>{
    const url = `${environment.config.URL_API}/cadastroLogin/login`;
    return this.httpClient.post<Usuario>(url, usuario).pipe(
      map(obj => obj),
      catchError( (e) => this.errorHandler(e))
    );
  }

  errorHandler(e: any): Observable<any>{
    this.showMessage('Ocorreu um erro!', true );
    return EMPTY;
  }

  showMessage(msg: string, isError: boolean = false): void{
    this.snackbar.open(msg, 'X', {
      duration: 3000,
      horizontalPosition: 'center',
      verticalPosition: 'bottom',
      panelClass: isError ? ['msg-error'] : ['msg-success'],
    });
  }
}

