import {UsuarioDao} from './usuario-dao';

export class PessoafisicaDto {
  idPess: number;
  nome: string;
  cpf: string;
  sexo: string;
  idade: number;
  usuario: UsuarioDao;
}
