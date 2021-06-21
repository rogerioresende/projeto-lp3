import {UsuarioDao} from './usuario-dao';

export class EmpresaDto {
  idEmp: number;
  razaoSocial: string;
  nomeFant: string;
  cnpj: string;
  usuario: UsuarioDao;
  }
