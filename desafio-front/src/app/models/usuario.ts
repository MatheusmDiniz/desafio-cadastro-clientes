export interface IUsuario {
    usuario?: string;
    senha?: string;
}

export class Usuario implements IUsuario {
    constructor(
        public usuario?: string,
        public senha?: string
    ) { }
}