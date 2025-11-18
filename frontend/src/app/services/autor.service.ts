import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Autor } from '../models/autor.model';

@Injectable({
  providedIn: 'root'
})
export class AutorService {
  private apiUrl = 'http://localhost:8080/api/autores';

  constructor(private http: HttpClient) { }

  listar(): Observable<Autor[]> {
    return this.http.get<Autor[]>(this.apiUrl);
  }

  buscarPorId(id: number): Observable<Autor> {
    return this.http.get<Autor>(`${this.apiUrl}/${id}`);
  }

  crear(autor: Autor): Observable<Autor> {
    return this.http.post<Autor>(this.apiUrl, autor);
  }

  actualizar(id: number, autor: Autor): Observable<Autor> {
    return this.http.put<Autor>(`${this.apiUrl}/${id}`, autor);
  }

  eliminar(id: number): Observable<void> {
    return this.http.delete<void>(`${this.apiUrl}/${id}`);
  }

  buscarPorNombre(nombre: string): Observable<Autor[]> {
    return this.http.get<Autor[]>(`${this.apiUrl}/buscar/nombre?nombre=${nombre}`);
  }

  buscarPorNacionalidad(nacionalidad: string): Observable<Autor[]> {
    return this.http.get<Autor[]>(`${this.apiUrl}/buscar/nacionalidad?nacionalidad=${nacionalidad}`);
  }
}
