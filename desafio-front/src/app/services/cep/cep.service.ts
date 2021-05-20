import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { IEndereco } from 'src/app/models/endereco';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class CepService {

  constructor(private httpClient: HttpClient) { }

  public buscarEnderecoPorCep(cep: string): Observable<any> {
    return this.httpClient.get(`${environment.cepUrlApi}${cep}/json/`);
  }
}