import { Routes } from '@angular/router';
import { HomeComponent } from './components/home/home.component';
import { LibrosListaComponent } from './components/libros/libros-lista.component';
import { AutoresListaComponent } from './components/autores/autores-lista.component';

export const routes: Routes = [
  { path: '', component: HomeComponent },
  { path: 'libros', component: LibrosListaComponent },
  { path: 'autores', component: AutoresListaComponent },
  { path: '**', redirectTo: '' }
];
