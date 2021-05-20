import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Router } from '@angular/router';
import { JwtHelperService } from '@auth0/angular-jwt';
import { Observable } from 'rxjs';
import { IUsuario } from 'src/app/models/usuario';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  jwtHelper: JwtHelperService = new JwtHelperService();

  constructor(private httpClient: HttpClient, private router: Router) { }

  public login(usuario: IUsuario): Observable<any> {
    return this.httpClient.post(
      `${environment.baseUrlApi}/login`, usuario,
      {
        observe: 'response',
        responseType: 'text'
      });
  }

  refreshToken() {
    return this.httpClient.post(
      `${environment.baseUrlApi}/auth/refresh_token`,
      {},
      {
        observe: 'response',
        responseType: 'text'
      }).subscribe(res => {
        this.successfulLogin(res.headers.get('Authorization'));
      })
  }

  successfulLogin(authorizationValue: string) {
    let token = authorizationValue.substring(7);
    sessionStorage.setItem('token', token);
  }

  logout() {
    sessionStorage.removeItem('token');
    this.router.navigate(['login']);
  }

  possuiPerfil(perfil): boolean {
    return this.getPerfis().includes(perfil);
  }

  getPerfis() {
    let token = sessionStorage.getItem('token');
    if (token) {
      return this.jwtHelper.decodeToken(token).perfis;
    }
    return null;
  }
}
