import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Cliente, ICliente } from 'src/app/models/cliente';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class ClienteService {

  constructor(private httpClient: HttpClient) { }

  buscarTodos(): Observable<Cliente[]> {
    return this.httpClient.get<Cliente[]>(`${environment.baseUrlApi}/cliente`);
  }

  buscarPorId(id: number): Observable<Cliente> {
    return this.httpClient.get<Cliente>(`${environment.baseUrlApi}/cliente/${id}`);
  }

  public salvar(cliente: ICliente): Observable<any> {
    return this.httpClient.post(
      `${environment.baseUrlApi}/cliente`, cliente,
      {
        observe: 'response',
        responseType: 'text'
      });
  }

  public atualizar(cliente: ICliente, id: number): Observable<any> {
    return this.httpClient.put(
      `${environment.baseUrlApi}/cliente/${id}`, cliente,
      {
        observe: 'response',
        responseType: 'text'
      });
  }

  deletarPorId(id: number): Observable<any> {
    return this.httpClient.delete<any>(`${environment.baseUrlApi}/cliente/${id}`);
  }
}
