import { Component, OnInit } from '@angular/core';
import {MAT_MOMENT_DATE_ADAPTER_OPTIONS, MAT_MOMENT_DATE_FORMATS, MomentDateAdapter} from '@angular/material-moment-adapter';
import {FormBuilder, FormControl, FormGroup, FormGroupDirective, NgForm, Validators} from '@angular/forms';
import {ActivatedRoute, Params, Router} from '@angular/router';
import {DateAdapter, ErrorStateMatcher, MAT_DATE_FORMATS, MAT_DATE_LOCALE} from '@angular/material/core';
import {Subscription} from 'rxjs';
import {VagasempregoService} from '../vagasemprego.service';
import {VagasDto} from '../../../model/vagas-dto';
import {EmpresaDto} from '../../../model/empresa-dto';

/** Error when invalid control is dirty, touched, or submitted. */
export class MyErrorStateMatcher implements ErrorStateMatcher {
  isErrorState(control: FormControl | null, form: FormGroupDirective | NgForm | null): boolean {
    const isSubmitted = form && form.submitted;
    return !!(control && control.invalid && (control.dirty || control.touched || isSubmitted));
  }
}

@Component({
  selector: 'app-vagas-detalhe',
  templateUrl: './vagas-detalhe.component.html',
  styleUrls: ['./vagas-detalhe.component.css'],
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
export class VagasDetalheComponent implements OnInit, ErrorStateMatcher {

  constructor(private vagasempregoService: VagasempregoService,
              private fb: FormBuilder,
              private router: Router,
              private route: ActivatedRoute,
  ) {
  }

  vaga: VagasDto;

  preenchida = [
    {vagaPreenchida: true, nome: 'Sim'},
    {vagaPreenchida: false, nome: 'Não'}
  ];

  empresa: EmpresaDto;

  formVaga: FormGroup;

  matcher = new MyErrorStateMatcher();

  inscricao: Subscription;

  ngOnInit(): void {
    this.inscricao = this.route.params.subscribe(
      (params: Params) => {
        const id: number = +params.idVaga;
        if (id) {
          this.vagasempregoService.bucarVagaEmpregoPorId(id).subscribe(dados => {
            this.vaga = dados;
            this.formVaga = this.fb.group({
              idVaga: [this.vaga.idVaga],
              nomeEmp: [this.vaga.nomeEmp, Validators.required],
              espVaga: [this.vaga.espVaga, Validators.required],
              nivelEsco: [this.vaga.espVaga, Validators.required],
              nivelTec: [this.vaga.espVaga, Validators.required],
              vagaPreenchida: [this.vaga.vagaPreenchida, Validators.required]
            });
          }, error => {console.error(error); });
        } else {
          this.vaga = {
            idVaga: null,
            nomeEmp: '',
            espVaga: '',
            nivelEsco: '',
            nivelTec: '',
            vagaPreenchida: false,
          };
          this.formVaga = this.fb.group({
            idVaga: [this.vaga.idVaga],
            nomeEmp: [this.vaga.nomeEmp, Validators.required],
            espVaga: [this.vaga.espVaga, Validators.required],
            nivelEsco: [this.vaga.espVaga, Validators.required],
            nivelTec: [this.vaga.espVaga, Validators.required],
            vagaPreenchida: [this.vaga.vagaPreenchida, Validators.required]
          });
        }
      });
  }

  onSubmit(): void {
    this.vaga = this.formVaga.value;
    if (this.vaga.idVaga === null){
      this.vagasempregoService.cadastrarVagasEmprego(this.vaga).subscribe(() => {
        this.vagasempregoService.showMessage('Vaga salvo com sucesso', false);
      });
      this.router.navigate(['/vagasempregoempresa']).then(() => {
        window.location.reload();
      });
    }else{
      this.vagasempregoService.editarVagasEmprego(this.vaga).subscribe(() => {
        this.vagasempregoService.showMessage('Atualizado com sucesso', false);
      });
      this.router.navigate(['/vagasempregoempresa']).then(() => {
        window.location.reload();
      });
    }
  }

  isErrorState(control: FormControl | null, form: FormGroupDirective | NgForm | null): boolean {
    return false;
  }

  get getControl(){
    return this.formVaga.controls;
  }

  voltar(): void {
    this.router.navigate(['/vagasempregoempresa']);
  }

}
