export interface IContato {
  id?: number;
  numero?: string;
  tipo?: string;

}

export class Contato implements IContato {
  constructor(
      public id?: number,
      public numero?: string,
      public tipo?: string
  ) { }
}