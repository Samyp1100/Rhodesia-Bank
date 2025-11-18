import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterLink } from '@angular/router';
import { FormsModule } from '@angular/forms';
import { AutorService } from '../../services/autor.service';
import { Autor } from '../../models/autor.model';

@Component({
  selector: 'app-autores-lista',
  standalone: true,
  imports: [CommonModule, RouterLink, FormsModule],
  templateUrl: './autores-lista.component.html',
  styleUrls: ['./autores-lista.component.css']
})
export class AutoresListaComponent implements OnInit {
  autores: Autor[] = [];
  autorSeleccionado: Autor | null = null;
  modoEdicion = false;
  mostrarFormulario = false;

  nuevoAutor: Autor = {
    nombre: '',
    nacionalidad: ''
  };

  constructor(private autorService: AutorService) {}

  ngOnInit(): void {
    this.cargarAutores();
  }

  cargarAutores(): void {
    this.autorService.listar().subscribe({
      next: (data) => {
        this.autores = data;
      },
      error: (err) => {
        console.error('Error al cargar autores:', err);
        alert('Error al cargar los autores');
      }
    });
  }

  mostrarFormularioCrear(): void {
    this.modoEdicion = false;
    this.mostrarFormulario = true;
    this.nuevoAutor = { nombre: '', nacionalidad: '' };
  }

  mostrarFormularioEditar(autor: Autor): void {
    this.modoEdicion = true;
    this.mostrarFormulario = true;
    this.autorSeleccionado = autor;
    this.nuevoAutor = { ...autor };
  }

  cancelar(): void {
    this.mostrarFormulario = false;
    this.modoEdicion = false;
    this.autorSeleccionado = null;
    this.nuevoAutor = { nombre: '', nacionalidad: '' };
  }

  guardar(): void {
    if (!this.nuevoAutor.nombre.trim()) {
      alert('El nombre es obligatorio');
      return;
    }

    if (this.modoEdicion && this.autorSeleccionado?.id) {
      this.autorService.actualizar(this.autorSeleccionado.id, this.nuevoAutor).subscribe({
        next: () => {
          this.cargarAutores();
          this.cancelar();
          alert('Autor actualizado exitosamente');
        },
        error: (err) => {
          console.error('Error al actualizar autor:', err);
          alert('Error al actualizar el autor');
        }
      });
    } else {
      this.autorService.crear(this.nuevoAutor).subscribe({
        next: () => {
          this.cargarAutores();
          this.cancelar();
          alert('Autor creado exitosamente');
        },
        error: (err) => {
          console.error('Error al crear autor:', err);
          alert('Error al crear el autor');
        }
      });
    }
  }

  eliminar(id: number | undefined): void {
    if (!id) return;

    if (confirm('¿Está seguro de que desea eliminar este autor?')) {
      this.autorService.eliminar(id).subscribe({
        next: () => {
          this.cargarAutores();
          alert('Autor eliminado exitosamente');
        },
        error: (err) => {
          console.error('Error al eliminar autor:', err);
          alert('Error al eliminar el autor');
        }
      });
    }
  }
}
