import { Contato } from './contato';
import { Endereco } from './endereco';

export interface ICliente {
  id?: number;
  nome?: string;
  cpf?: string;
  endereco?: Endereco;
  contato?: Array<Contato>;
  emails?: Array<string>;
}

export class Cliente implements ICliente {
  constructor(
    public id?: number,
    public nome?: string,
    public cpf?: string,
    public endereco: Endereco = new Endereco(),
    public contato: Array<Contato> = [],
    public emails: Array<string> = []
  ) { }
}