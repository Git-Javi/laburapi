export interface ITipoPresencia {
    id:number;
    nombre:string | null;
}

export class TipoPresencia implements ITipoPresencia {
    constructor(
        public id: number,
        public nombre: string
    ) {}
}