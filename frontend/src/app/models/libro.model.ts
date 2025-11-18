import { Autor } from './autor.model';

export interface Libro {
  id?: number;
  titulo: string;
  isbn: string;
  anio: number;
  autores?: Autor[];
}

export interface LibroCreate {
  titulo: string;
  isbn: string;
  anio: number;
  autoresIds: number[];
}
