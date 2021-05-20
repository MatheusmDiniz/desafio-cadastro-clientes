import { Injectable } from '@angular/core';
import {
  HttpInterceptor,
  HttpRequest,
  HttpResponse,
  HttpHandler,
  HttpEvent,
  HttpErrorResponse
} from '@angular/common/http';

import { Observable, throwError } from 'rxjs';
import { map, catchError } from 'rxjs/operators';
import { NotifierService } from 'angular-notifier';
import { Router } from '@angular/router';

@Injectable()
export class ErrorInterceptor implements HttpInterceptor {

  private readonly notifier: NotifierService;

  constructor(
    private router: Router,
    notifierService: NotifierService,

  ) {
    this.notifier = notifierService;
  }

  intercept(request: HttpRequest<unknown>, next: HttpHandler): Observable<HttpEvent<unknown>> {

    return next.handle(request).pipe(
      map((event: HttpEvent<any>) => {
        if (event instanceof HttpResponse) {
          console.log('event--->>>', event);
        }
        return event;
      }),
      catchError((error: HttpErrorResponse) => {
        let data = JSON.parse(error.error);

        switch (error.status) {
          case 401:
            this.handle401();
            break;

          case 400:
            this.handle400(data);
            break;

          case 403:
            this.handle403();
            break;

          case 422:
            this.handle422(data);
            break;

          default:
            this.handleDefaultError(data);
        }

        return throwError(error);
      }));
  }

  private handle401() {
    this.notifier.notify("warning", "Falha de Autenticação! E-mail ou senha Incorretos");
  }

  private handle400(data) {
    data.errors.forEach(erro => {
      this.notifier.notify("warning", erro.message);
    });
  }

  private handle403() {
    this.notifier.notify("warning", "Acesso Restrito! Sem permissão para acessar esse recurso");
    sessionStorage.removeItem('token');
    this.router.navigate(['login']);
  }

  private handle422(data) {
    data.errors.forEach(erro => {
      this.notifier.notify("warning", erro.message);
    });
  }

  private handleDefaultError(data) {
    this.notifier.notify("warning", "Ocorreu um erro inesperado, tente novamente mais tarde");
  }

}