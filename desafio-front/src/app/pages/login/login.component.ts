import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { NotifierService } from 'angular-notifier';
import { Usuario } from 'src/app/models/usuario';
import { AuthService } from 'src/app/services/auth/auth.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  usuario: Usuario = new Usuario();
  form: FormGroup;

  constructor(
    private router: Router,
    private authService: AuthService,
    private nt: NotifierService,
    public formBuilder: FormBuilder
  ) { }


  ngOnInit() {
    this.montaFormBuilder();
  }

  private montaFormBuilder() {
    this.form = this.formBuilder.group({
      usuario: [this.usuario ? this.usuario.usuario : "", [Validators.required]],
      senha: [this.usuario ? this.usuario.senha : "", [Validators.required]],
    });
  }

  entrar() {
    if (this.form.invalid) {
      return this.nt.notify("error", "Preencha todos os campos");
    }
    this.authService.login(this.usuario).subscribe(response => {
      this.authService.successfulLogin(response.headers.get('Authorization'));
      this.router.navigate(['clientes']);
    },
      error => console.log(error));
  }

}
