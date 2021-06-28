import { Component, OnInit } from '@angular/core';
import {DateAdapter, ErrorStateMatcher, MAT_DATE_FORMATS, MAT_DATE_LOCALE} from '@angular/material/core';
import {FormBuilder, FormControl, FormGroup, FormGroupDirective, NgForm, Validators} from '@angular/forms';
import {MAT_MOMENT_DATE_ADAPTER_OPTIONS, MAT_MOMENT_DATE_FORMATS, MomentDateAdapter} from '@angular/material-moment-adapter';
import {UsuarioService} from '../usuario.service';
import {ActivatedRoute, Params, Router} from '@angular/router';
import {Subscription} from 'rxjs';
import {PessoafisicaDto} from '../../../model/pessoafisica-dto';
import {UsuarioDao} from '../../../model/usuario-dao';
import {AuthGuardService} from '../../guards/auth.guard.service';
import {Curriculo} from '../../../model/curriculo';


export class MyErrorStateMatcher implements ErrorStateMatcher {
  isErrorState(control: FormControl | null, form: FormGroupDirective | NgForm | null): boolean {
    const isSubmitted = form && form.submitted;
    return !!(control && control.invalid && (control.dirty || control.touched || isSubmitted));
  }
}
@Component({
  selector: 'app-pessoafisica-detalhe',
  templateUrl: './pessoafisica-detalhe.component.html',
  styleUrls: ['./pessoafisica-detalhe.component.css'],

  providers: [
    // The locale would typically be provided on the root module of your application. We do it at
    // the component level here, due to limitations of our example generation script.
    {provide: MAT_DATE_LOCALE, useValue: 'pt-BR'},

    // `MomentDateAdapter` and `MAT_MOMENT_DATE_FORMATS` can be automatically provided by importing
    // `MatMomentDateModule` in your applications root module. We provide it at the component level
    // here, due to limitations of our example generation script.
    {
      provide: DateAdapter,
      useClass: MomentDateAdapter,
      deps: [MAT_DATE_LOCALE, MAT_MOMENT_DATE_ADAPTER_OPTIONS]
    },
    {provide: MAT_DATE_FORMATS, useValue: MAT_MOMENT_DATE_FORMATS},
  ],
})

export class PessoafisicaDetalheComponent implements OnInit {

  constructor(private pessoafisicaService: UsuarioService,
              private fb: FormBuilder,
              private router: Router,
              private route: ActivatedRoute,
              private authService: AuthGuardService
  ) {
  }
  pessoafisica: PessoafisicaDto;

  usuario: UsuarioDao;

  curriculo: Curriculo;

  formPessoa: FormGroup;

  matcher = new MyErrorStateMatcher();

  inscricao: Subscription;

  ngOnInit(): void {
    this.inscricao = this.route.params.subscribe(
      (params: Params) => {
        const idPess: number = +params.idPess;
        if (idPess) {
          this.pessoafisicaService.bucarPessoaFisicaPorId(idPess).subscribe(dados => {
            this.pessoafisica = dados;
            this.formPessoa = this.fb.group({
              idPess: [this.pessoafisica.idPess],
              idCurr: [this.pessoafisica.curriculo.idCurr],
              nome: [this.pessoafisica.nome, [Validators.required, Validators.minLength(3)]],
              cpf: [this.pessoafisica.cpf, [Validators.required]],
              sexo: [this.pessoafisica.sexo, Validators.required],
              idade: [this.pessoafisica.idade, Validators.required],
              grauEsco: [this.pessoafisica.curriculo.grauEsco, Validators.required],
              infoTec: [this.pessoafisica.curriculo.infoTec, Validators.required],
              formaAcade: [this.pessoafisica.curriculo.formaAcade, Validators.required]
            });
          }, error => {
            console.error(error);
          });
        } else {
          this.usuario = {
            email: '',
            senha: '',
          };
          this.curriculo = {
            idCurr: null,
            grauEsco: '',
            infoTec: '',
            formaAcade: ''
          },
          this.pessoafisica = {
            idPess: null,
            nome: '',
            cpf: '',
            sexo: '',
            idade:  null,
            usuario: this.usuario,
            curriculo: this.curriculo
          };
          this.formPessoa = this.fb.group({     // {5}
            idPess: [this.pessoafisica.idPess],
            idCurr: [this.pessoafisica.curriculo.idCurr],
            nome: [this.pessoafisica.nome, Validators.required],
            cpf: [this.pessoafisica.cpf, Validators.required],
            sexo: [this.pessoafisica.sexo, [Validators.required]],
            idade: [this.pessoafisica.idade, [Validators.required]],
            grauEsco: [this.pessoafisica.curriculo.grauEsco, Validators.required],
            infoTec: [this.pessoafisica.curriculo.infoTec, Validators.required],
            formaAcade: [this.pessoafisica.curriculo.formaAcade, Validators.required]
          });
        }
      });
  }

  onSubmit(): void {
    this.curriculo = {
      idCurr: this.formPessoa.value.idCurr,
      grauEsco: this.formPessoa.value.grauEsco,
      infoTec: this.formPessoa.value.infoTec,
      formaAcade: this.formPessoa.value.formaAcade
    },
    this.pessoafisica = {
      idPess: this.formPessoa.value.idPess,
      nome: this.formPessoa.value.nome,
      cpf: this.formPessoa.value.cpf,
      sexo: this.formPessoa.value.sexo,
      idade:  this.formPessoa.value.idade,
      usuario: this.usuario,
      curriculo: this.curriculo
    };
    if (this.pessoafisica.idPess === null) {
      this.pessoafisicaService.cadastrarPessoaFisica(this.pessoafisica).subscribe(() => {
        this.pessoafisicaService.showMessage('Pessoa fisica salvo com sucesso', false);
        this.usuario = {
          email: localStorage.getItem('email_acesso'),
          senha: localStorage.getItem('senha_acesso')
        };
        this.authService.login(this.usuario, false);
      });
    } else {
      this.pessoafisicaService.editarPessoaFisica(this.pessoafisica).subscribe(() => {
        this.pessoafisicaService.showMessage('Pessoa fisica atualizada com sucesso', false);
      });
      this.router.navigate(['/usuario-pessoa']).then(() => {
        window.location.reload();
      });
    }
  }

  isErrorState(control: FormControl | null, form: FormGroupDirective | NgForm | null): boolean {
    return false;
  }

  get getControl(){
    return this.formPessoa.controls;
  }
  voltar(): void {this.router.navigate(['/usuario-pessoa']);
  }
}


