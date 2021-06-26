import {UsuarioDao} from './usuario-dao';

export class VagasDto {
  idVaga: number;
  nomeEmp: string;
  nivelEsco: string;
  nivelTec: string;
  espVaga: string;
  usuario: UsuarioDao;
}
