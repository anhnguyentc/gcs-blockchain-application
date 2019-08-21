import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { AdmUserTokenLogin } from 'app/shared/model/adm-user-token-login.model';
import { AdmUserTokenLoginService } from './adm-user-token-login.service';
import { AdmUserTokenLoginComponent } from './adm-user-token-login.component';
import { AdmUserTokenLoginDetailComponent } from './adm-user-token-login-detail.component';
import { AdmUserTokenLoginUpdateComponent } from './adm-user-token-login-update.component';
import { AdmUserTokenLoginDeletePopupComponent } from './adm-user-token-login-delete-dialog.component';
import { IAdmUserTokenLogin } from 'app/shared/model/adm-user-token-login.model';

@Injectable({ providedIn: 'root' })
export class AdmUserTokenLoginResolve implements Resolve<IAdmUserTokenLogin> {
  constructor(private service: AdmUserTokenLoginService) {}

  resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<IAdmUserTokenLogin> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        filter((response: HttpResponse<AdmUserTokenLogin>) => response.ok),
        map((admUserTokenLogin: HttpResponse<AdmUserTokenLogin>) => admUserTokenLogin.body)
      );
    }
    return of(new AdmUserTokenLogin());
  }
}

export const admUserTokenLoginRoute: Routes = [
  {
    path: '',
    component: AdmUserTokenLoginComponent,
    resolve: {
      pagingParams: JhiResolvePagingParams
    },
    data: {
      authorities: ['ROLE_USER'],
      defaultSort: 'id,asc',
      pageTitle: 'AdmUserTokenLogins'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: AdmUserTokenLoginDetailComponent,
    resolve: {
      admUserTokenLogin: AdmUserTokenLoginResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'AdmUserTokenLogins'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: AdmUserTokenLoginUpdateComponent,
    resolve: {
      admUserTokenLogin: AdmUserTokenLoginResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'AdmUserTokenLogins'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: AdmUserTokenLoginUpdateComponent,
    resolve: {
      admUserTokenLogin: AdmUserTokenLoginResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'AdmUserTokenLogins'
    },
    canActivate: [UserRouteAccessService]
  }
];

export const admUserTokenLoginPopupRoute: Routes = [
  {
    path: ':id/delete',
    component: AdmUserTokenLoginDeletePopupComponent,
    resolve: {
      admUserTokenLogin: AdmUserTokenLoginResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'AdmUserTokenLogins'
    },
    canActivate: [UserRouteAccessService],
    outlet: 'popup'
  }
];
