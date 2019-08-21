import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { BlcRegisterPrivateKey } from 'app/shared/model/blc-register-private-key.model';
import { BlcRegisterPrivateKeyService } from './blc-register-private-key.service';
import { BlcRegisterPrivateKeyComponent } from './blc-register-private-key.component';
import { BlcRegisterPrivateKeyDetailComponent } from './blc-register-private-key-detail.component';
import { BlcRegisterPrivateKeyUpdateComponent } from './blc-register-private-key-update.component';
import { BlcRegisterPrivateKeyDeletePopupComponent } from './blc-register-private-key-delete-dialog.component';
import { IBlcRegisterPrivateKey } from 'app/shared/model/blc-register-private-key.model';

@Injectable({ providedIn: 'root' })
export class BlcRegisterPrivateKeyResolve implements Resolve<IBlcRegisterPrivateKey> {
  constructor(private service: BlcRegisterPrivateKeyService) {}

  resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<IBlcRegisterPrivateKey> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        filter((response: HttpResponse<BlcRegisterPrivateKey>) => response.ok),
        map((blcRegisterPrivateKey: HttpResponse<BlcRegisterPrivateKey>) => blcRegisterPrivateKey.body)
      );
    }
    return of(new BlcRegisterPrivateKey());
  }
}

export const blcRegisterPrivateKeyRoute: Routes = [
  {
    path: '',
    component: BlcRegisterPrivateKeyComponent,
    resolve: {
      pagingParams: JhiResolvePagingParams
    },
    data: {
      authorities: ['ROLE_USER'],
      defaultSort: 'id,asc',
      pageTitle: 'BlcRegisterPrivateKeys'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: BlcRegisterPrivateKeyDetailComponent,
    resolve: {
      blcRegisterPrivateKey: BlcRegisterPrivateKeyResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'BlcRegisterPrivateKeys'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: BlcRegisterPrivateKeyUpdateComponent,
    resolve: {
      blcRegisterPrivateKey: BlcRegisterPrivateKeyResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'BlcRegisterPrivateKeys'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: BlcRegisterPrivateKeyUpdateComponent,
    resolve: {
      blcRegisterPrivateKey: BlcRegisterPrivateKeyResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'BlcRegisterPrivateKeys'
    },
    canActivate: [UserRouteAccessService]
  }
];

export const blcRegisterPrivateKeyPopupRoute: Routes = [
  {
    path: ':id/delete',
    component: BlcRegisterPrivateKeyDeletePopupComponent,
    resolve: {
      blcRegisterPrivateKey: BlcRegisterPrivateKeyResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'BlcRegisterPrivateKeys'
    },
    canActivate: [UserRouteAccessService],
    outlet: 'popup'
  }
];
