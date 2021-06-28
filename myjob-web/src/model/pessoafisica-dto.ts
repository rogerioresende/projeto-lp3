import {UsuarioDao} from './usuario-dao';
import {Curriculo} from './curriculo';

export class PessoafisicaDto {
  idPess: number;
  nome: string;
  cpf: string;
  sexo: string;
  idade: number;
  usuario: UsuarioDao;
  curriculo: Curriculo;
}
