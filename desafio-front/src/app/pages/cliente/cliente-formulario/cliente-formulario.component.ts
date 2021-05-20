import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { NotifierService } from 'angular-notifier';
import { Cliente } from 'src/app/models/cliente';
import { Contato } from 'src/app/models/contato';
import { siglasUnidadeFederativa } from 'src/app/models/enum/sigla-unidade-federativa copy';
import { CepService } from 'src/app/services/cep/cep.service';
import { ClienteService } from 'src/app/services/cliente/cliente.service';

@Component({
  selector: 'app-cliente-formulario',
  templateUrl: './cliente-formulario.component.html',
  styleUrls: ['./cliente-formulario.component.css']
})
export class ClienteFormularioComponent implements OnInit {

  id: any;
  acao: string = "Novo Cliente";
  titulo: string = "Cadastrar o Cliente";
  cliente: Cliente = new Cliente();
  form: FormGroup;

  contato: Contato = new Contato();

  email: string = "";

  ufs: any = siglasUnidadeFederativa;

  constructor(
    private activatedRoute: ActivatedRoute,
    private router: Router,
    private clienteService: ClienteService,
    private nt: NotifierService,
    public formBuilder: FormBuilder,
    private cepService: CepService
  ) {
    this.id = this.activatedRoute.snapshot.paramMap.get("id");
  }

  ngOnInit(): void {
    this.montaFormBuilder();
    this.buscarCliente();
  }

  private montaFormBuilder() {
    this.form = this.formBuilder.group({
      nome: ["", [Validators.required]],
      cpf: ["", [Validators.required]],
      cep: ["", [Validators.required]],
      logradouro: ["", [Validators.required]],
      bairro: ["", [Validators.required]],
      cidade: ["", [Validators.required]],
      uf: ["", [Validators.required]],
      complemento: [""],
    });
  }

  private buscarCliente() {
    if (this.id) {
      this.acao = "Alterar";
      this.titulo = "Alterar Cliente";
      this.clienteService.buscarPorId(this.id).subscribe(obj => {
        this.cliente = obj;
      });
    }
  }

  adicionarTelefone() {
    if (this.contato) {

      if (!this.contato.numero) {
        return this.nt.notify("warning", "Informe um número de telefone.");
      }

      if (!this.contato.tipo) {
        return this.nt.notify("warning", "Informe um tipo de telefone.");
      }

      if (this.cliente.contato.filter(contato => contato.numero === this.contato.numero).length) {
        return this.nt.notify("warning", "Já foi informado esse número de telefone.");
      }

      this.cliente.contato.push(this.contato);
      this.contato = new Contato();
    }
  }

  adicionarEmail() {
    if (this.email) {

      if (!this.validateEmail(this.email)) {
        return this.nt.notify("warning", "E-mail informado é inválido!");
      }

      if (this.cliente.emails.filter(email => email === this.email).length) {
        return this.nt.notify("warning", "Já foi informado esse email");
      }

      this.cliente.emails.push(this.email);
      this.email = "";
    }
  }

  removerTelefone(numero) {
    let index = this.cliente.contato.findIndex(obj => obj.numero == numero);
    if (index >= 0) {
      this.cliente.contato.splice(index, 1);
    }
  }

  removerEmail(email) {
    let index = this.cliente.emails.findIndex(obj => obj === email);
    if (index >= 0) {
      this.cliente.emails.splice(index, 1);
    }
  }

  save() {

    if (!this.cliente.emails || !this.cliente.emails.length) {
      return this.nt.notify("error", "É necessário informar no mínimo um e-mail!");
    }

    if (!this.cliente.contato || !this.cliente.contato.length) {
      return this.nt.notify("error", "É necessário informar no mínimo um contato");
    }

    if (this.id) {
      this.clienteService.atualizar(this.cliente, this.id).subscribe(() => {
        this.nt.notify("sucess", "Cliente atualizado com sucesso!");
        this.router.navigate(['clientes']);
      })
    } else {
      this.clienteService.salvar(this.cliente).subscribe(() => {
        this.nt.notify("sucess", "Cliente cadastrado com sucesso!");
        this.router.navigate(['clientes']);
      })
    }
  }

  buscarEnderecoPorCep(event: any) {
    let cep = event.target.value.replace(/[^0-9]+/g, '');
    if (cep.length === 8) {
      this.cepService.buscarEnderecoPorCep(cep).subscribe(obj => {
        this.cliente.endereco.bairro = obj.bairro;
        this.cliente.endereco.logradouro = obj.logradouro;
        this.cliente.endereco.uf = obj.uf;
        this.cliente.endereco.cidade = obj.localidade
      });
    }
  }

  private validateEmail(email) {
    var re = /\S+@\S+\.\S+/;
    return re.test(email);
  }

  isCelular(){
    if(this.contato.tipo === "CELULAR"){
      return "(99)9 9999-9999";
    }
    return "(99)9999-9999";
  }

}