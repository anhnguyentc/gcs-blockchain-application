import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { BlcGiayKhamSucKhoe } from 'app/shared/model/blc-giay-kham-suc-khoe.model';
import { BlcGiayKhamSucKhoeService } from './blc-giay-kham-suc-khoe.service';
import { BlcGiayKhamSucKhoeComponent } from './blc-giay-kham-suc-khoe.component';
import { BlcGiayKhamSucKhoeDetailComponent } from './blc-giay-kham-suc-khoe-detail.component';
import { BlcGiayKhamSucKhoeUpdateComponent } from './blc-giay-kham-suc-khoe-update.component';
import { BlcGiayKhamSucKhoeDeletePopupComponent } from './blc-giay-kham-suc-khoe-delete-dialog.component';
import { IBlcGiayKhamSucKhoe } from 'app/shared/model/blc-giay-kham-suc-khoe.model';

@Injectable({ providedIn: 'root' })
export class BlcGiayKhamSucKhoeResolve implements Resolve<IBlcGiayKhamSucKhoe> {
  constructor(private service: BlcGiayKhamSucKhoeService) {}

  resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<IBlcGiayKhamSucKhoe> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        filter((response: HttpResponse<BlcGiayKhamSucKhoe>) => response.ok),
        map((blcGiayKhamSucKhoe: HttpResponse<BlcGiayKhamSucKhoe>) => blcGiayKhamSucKhoe.body)
      );
    }
    return of(new BlcGiayKhamSucKhoe());
  }
}

export const blcGiayKhamSucKhoeRoute: Routes = [
  {
    path: '',
    component: BlcGiayKhamSucKhoeComponent,
    resolve: {
      pagingParams: JhiResolvePagingParams
    },
    data: {
      authorities: ['ROLE_USER'],
      defaultSort: 'id,asc',
      pageTitle: 'BlcGiayKhamSucKhoes'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: BlcGiayKhamSucKhoeDetailComponent,
    resolve: {
      blcGiayKhamSucKhoe: BlcGiayKhamSucKhoeResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'BlcGiayKhamSucKhoes'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: BlcGiayKhamSucKhoeUpdateComponent,
    resolve: {
      blcGiayKhamSucKhoe: BlcGiayKhamSucKhoeResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'BlcGiayKhamSucKhoes'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: BlcGiayKhamSucKhoeUpdateComponent,
    resolve: {
      blcGiayKhamSucKhoe: BlcGiayKhamSucKhoeResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'BlcGiayKhamSucKhoes'
    },
    canActivate: [UserRouteAccessService]
  }
];

export const blcGiayKhamSucKhoePopupRoute: Routes = [
  {
    path: ':id/delete',
    component: BlcGiayKhamSucKhoeDeletePopupComponent,
    resolve: {
      blcGiayKhamSucKhoe: BlcGiayKhamSucKhoeResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'BlcGiayKhamSucKhoes'
    },
    canActivate: [UserRouteAccessService],
    outlet: 'popup'
  }
];
