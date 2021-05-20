import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AuthAdminGuard } from './guards/auth-admin/auth-admin.guard';
import { AuthGuard } from './guards/auth/auth.guard';
import { LoggedGuard } from './guards/logged/logged.guard';
import { ClienteFormularioComponent } from './pages/cliente/cliente-formulario/cliente-formulario.component';
import { ClienteListaComponent } from './pages/cliente/cliente-lista/cliente-lista.component';
import { LoginComponent } from './pages/login/login.component';

const routes: Routes = [
  { path: 'login', component: LoginComponent, canActivate: [LoggedGuard] },
  { path: '', redirectTo: '/clientes', pathMatch: 'full', canActivate: [AuthGuard] },
  { path: 'clientes', component: ClienteListaComponent, canActivate: [AuthGuard] },
  { path: 'clientes/formulario', component: ClienteFormularioComponent, canActivate: [AuthAdminGuard] },
  { path: 'clientes/formulario/:id', component: ClienteFormularioComponent, canActivate: [AuthAdminGuard] },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
