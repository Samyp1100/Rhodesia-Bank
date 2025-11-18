import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterLink } from '@angular/router';
import { FormsModule } from '@angular/forms';
import { LibroService } from '../../services/libro.service';
import { AutorService } from '../../services/autor.service';
import { Libro, LibroCreate } from '../../models/libro.model';
import { Autor } from '../../models/autor.model';

@Component({
  selector: 'app-libros-lista',
  standalone: true,
  imports: [CommonModule, RouterLink, FormsModule],
  templateUrl: './libros-lista.component.html',
  styleUrls: ['./libros-lista.component.css']
})
export class LibrosListaComponent implements OnInit {
  libros: Libro[] = [];
  autores: Autor[] = [];
  libroSeleccionado: Libro | null = null;
  modoEdicion = false;
  mostrarFormulario = false;

  nuevoLibro: LibroCreate = {
    titulo: '',
    isbn: '',
    anio: new Date().getFullYear(),
    autoresIds: []
  };

  autoresSeleccionados: Set<number> = new Set();

  constructor(
    private libroService: LibroService,
    private autorService: AutorService
  ) {}

  ngOnInit(): void {
    this.cargarLibros();
    this.cargarAutores();
  }

  cargarLibros(): void {
    this.libroService.listar().subscribe({
      next: (data) => {
        this.libros = data;
      },
      error: (err) => {
        console.error('Error al cargar libros:', err);
        alert('Error al cargar los libros');
      }
    });
  }

  cargarAutores(): void {
    this.autorService.listar().subscribe({
      next: (data) => {
        this.autores = data;
      },
      error: (err) => {
        console.error('Error al cargar autores:', err);
      }
    });
  }

  mostrarFormularioCrear(): void {
    this.modoEdicion = false;
    this.mostrarFormulario = true;
    this.nuevoLibro = {
      titulo: '',
      isbn: '',
      anio: new Date().getFullYear(),
      autoresIds: []
    };
    this.autoresSeleccionados.clear();
  }

  mostrarFormularioEditar(libro: Libro): void {
    this.modoEdicion = true;
    this.mostrarFormulario = true;
    this.libroSeleccionado = libro;
    this.nuevoLibro = {
      titulo: libro.titulo,
      isbn: libro.isbn,
      anio: libro.anio,
      autoresIds: libro.autores?.map(a => a.id!) || []
    };
    this.autoresSeleccionados = new Set(this.nuevoLibro.autoresIds);
  }

  cancelar(): void {
    this.mostrarFormulario = false;
    this.modoEdicion = false;
    this.libroSeleccionado = null;
    this.nuevoLibro = {
      titulo: '',
      isbn: '',
      anio: new Date().getFullYear(),
      autoresIds: []
    };
    this.autoresSeleccionados.clear();
  }

  toggleAutor(autorId: number): void {
    if (this.autoresSeleccionados.has(autorId)) {
      this.autoresSeleccionados.delete(autorId);
    } else {
      this.autoresSeleccionados.add(autorId);
    }
  }

  isAutorSeleccionado(autorId: number): boolean {
    return this.autoresSeleccionados.has(autorId);
  }

  guardar(): void {
    if (!this.nuevoLibro.titulo.trim() || !this.nuevoLibro.isbn.trim()) {
      alert('El título y el ISBN son obligatorios');
      return;
    }

    this.nuevoLibro.autoresIds = Array.from(this.autoresSeleccionados);

    if (this.modoEdicion && this.libroSeleccionado?.id) {
      this.libroService.actualizar(this.libroSeleccionado.id, this.nuevoLibro).subscribe({
        next: () => {
          this.cargarLibros();
          this.cancelar();
          alert('Libro actualizado exitosamente');
        },
        error: (err) => {
          console.error('Error al actualizar libro:', err);
          alert('Error al actualizar el libro');
        }
      });
    } else {
      this.libroService.crear(this.nuevoLibro).subscribe({
        next: () => {
          this.cargarLibros();
          this.cancelar();
          alert('Libro creado exitosamente');
        },
        error: (err) => {
          console.error('Error al crear libro:', err);
          alert('Error al crear el libro');
        }
      });
    }
  }

  eliminar(id: number | undefined): void {
    if (!id) return;

    if (confirm('¿Está seguro de que desea eliminar este libro?')) {
      this.libroService.eliminar(id).subscribe({
        next: () => {
          this.cargarLibros();
          alert('Libro eliminado exitosamente');
        },
        error: (err) => {
          console.error('Error al eliminar libro:', err);
          alert('Error al eliminar el libro');
        }
      });
    }
  }

  obtenerNombresAutores(libro: Libro): string {
    if (!libro.autores || libro.autores.length === 0) {
      return 'Sin autores';
    }
    return libro.autores.map(a => a.nombre).join(', ');
  }
}
