import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Libro, LibroCreate } from '../models/libro.model';

@Injectable({
  providedIn: 'root'
})
export class LibroService {
  private apiUrl = 'http://localhost:8080/api/libros';

  constructor(private http: HttpClient) { }

  listar(): Observable<Libro[]> {
    return this.http.get<Libro[]>(this.apiUrl);
  }

  buscarPorId(id: number): Observable<Libro> {
    return this.http.get<Libro>(`${this.apiUrl}/${id}`);
  }

  crear(libro: LibroCreate): Observable<Libro> {
    return this.http.post<Libro>(this.apiUrl, libro);
  }

  actualizar(id: number, libro: LibroCreate): Observable<Libro> {
    return this.http.put<Libro>(`${this.apiUrl}/${id}`, libro);
  }

  eliminar(id: number): Observable<void> {
    return this.http.delete<void>(`${this.apiUrl}/${id}`);
  }

  buscarPorTitulo(titulo: string): Observable<Libro[]> {
    return this.http.get<Libro[]>(`${this.apiUrl}/buscar/titulo?titulo=${titulo}`);
  }

  buscarPorAnio(anio: number): Observable<Libro[]> {
    return this.http.get<Libro[]>(`${this.apiUrl}/buscar/anio?anio=${anio}`);
  }

  buscarPorAutor(autorId: number): Observable<Libro[]> {
    return this.http.get<Libro[]>(`${this.apiUrl}/buscar/autor/${autorId}`);
  }
}
