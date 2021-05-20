import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { NotifierService } from 'angular-notifier';
import { ConfirmationService } from 'primeng/api';
import { Cliente } from 'src/app/models/cliente';
import { perfis } from 'src/app/models/enum/perfis';
import { AuthService } from 'src/app/services/auth/auth.service';
import { ClienteService } from 'src/app/services/cliente/cliente.service';

@Component({
  selector: 'app-cliente-lista',
  templateUrl: './cliente-lista.component.html',
  styleUrls: ['./cliente-lista.component.css'],
  providers: [ConfirmationService]
})
export class ClienteListaComponent implements OnInit {

  private readonly notifier: NotifierService;

  clientes: Array<Cliente> = [];

  constructor(
    notifierService: NotifierService,
    private clienteService: ClienteService,
    private authService: AuthService,
    private confirmationService: ConfirmationService,
    private nt: NotifierService
  ) {
    this.notifier = notifierService;
  }

  ngOnInit(): void {
    this.buscarTodos();
  }

  logout() {
    this.authService.logout();
  }

  private buscarTodos() {
    this.clienteService.buscarTodos().subscribe(res => {
      this.clientes = res;
    });
  }

  confirmaExclusao(id: number) {
    this.confirmationService.confirm({
      message: `Você deseja excluir o cliente #${id}?`,
      accept: () => {
        this.clienteService.deletarPorId(id).subscribe(() => {
          this.nt.notify("sucess", `O usuário ${id} foi excluido com sucesso!`);
          this.buscarTodos();
        });
      }
    });
  }

  possuiPerfilAdmin() {
    return this.authService.possuiPerfil(perfis.ADMIN);
  }
}
