import { Component, OnInit } from '@angular/core';
import {FormBuilder, FormGroup, Validators} from '@angular/forms';
import {UsuarioDao} from '../../model/usuario-dao';
import {AuthGuardService} from '../guards/auth.guard.service';
import {Router} from '@angular/router';
import {CadastroService} from './cadastro.service';
import {Endereco} from '../../model/endereco';
import {Usuario} from '../../model/usuario';


@Component({
  selector: 'app-cadastro',
  templateUrl: './cadastro.component.html',
  styleUrls: ['./cadastro.component.css']
})
export class CadastroComponent implements OnInit {

  formCadastro: FormGroup;
  private formSubmitAttempt: boolean;
  private usuarioDao: UsuarioDao;
  private endereco: Endereco;
  private usuario: Usuario;

  constructor(
    private fb: FormBuilder,
    private authService: AuthGuardService,
    private router: Router,
    private cadastroService: CadastroService
  ) {}

  ngOnInit(): void {
    this.formCadastro = this.fb.group({
      usuario: ['', Validators.required],
      email: ['', Validators.required],
      senha: ['', Validators.required],
      estado: ['', Validators.required],
      bairro: ['', Validators.required],
      rua: ['', Validators.required],
      complemento: ['', Validators.required]
    });
  }

  isFieldInvalid(field: string) {
    return (
      (!this.formCadastro.get(field).valid && this.formCadastro.get(field).touched) ||
      (this.formCadastro.get(field).untouched && this.formSubmitAttempt)
    );
  }

  onSubmit(): void {
    if (this.formCadastro.valid) {
      this.endereco = {
        estado: this.formCadastro.value.estado,
        bairro: this.formCadastro.value.bairro,
        rua: this.formCadastro.value.rua,
        complemento: this.formCadastro.value.complemento,
        idEnd: null
      };
      this.usuarioDao = {
        email: this.formCadastro.value.email,
        senha: this.formCadastro.value.senha
      };
      this.usuario = {
        usuario: this.formCadastro.value.usuario,
        email: this.formCadastro.value.email,
        senha: this.formCadastro.value.senha,
        id: null,
        endereco: this.endereco
      };
      this.cadastroService.cadastrar(this.usuario).subscribe((dado) => {
        this.cadastroService.showMessage('Cadastro salvo com sucesso', false);
        localStorage.setItem('email_acesso', this.formCadastro.value.email);
        localStorage.setItem('senha_acesso', this.formCadastro.value.senha);
        this.router.navigate(['/acesso']);
      });
    }
    this.formSubmitAttempt = true;
  }

  voltar(): void {
    this.router.navigate(['/login']);
  }

}

