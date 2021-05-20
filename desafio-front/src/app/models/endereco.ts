export interface IEndereco {
  id?: number;
  cep?: string;
  logradouro?: string;
  bairro?: string;
  cidade?: string;
  complemento?: string;
  uf?: string;
}

export class Endereco implements IEndereco {
  constructor(
      public id?: number,
      public cep?: string,
      public logradouro?: string,
      public bairro?: string,
      public cidade?: string,
      public complemento?: string,
      public uf?: string
  ) { }
}