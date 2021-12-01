import { IPersona } from '../models/Persona';
import { ITipoPresencia } from '../models/TipoPresencia';

export interface IPresencia {
    id:number | null;
    inicio:Date;
    fin:Date | null;
    persona:IPersona;
    tipoPresencia:ITipoPresencia;
}

export class Presencia implements IPresencia {
    constructor(
        public id: number | null,
        public inicio: Date,
        public fin: Date | null, 
        public persona: IPersona,
        public tipoPresencia: ITipoPresencia
    ) {}
}