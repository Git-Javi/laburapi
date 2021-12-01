export interface IPersona {
    id:number;
    dni:string;
    nombre:string;
    telefono:string;
}

export class Persona implements IPersona {
    constructor(
        public id: number,
        public dni: string,
        public nombre: string,
        public telefono: string
    ) {}
}