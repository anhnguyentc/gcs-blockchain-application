import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { BlcGiayChungSinh } from 'app/shared/model/blc-giay-chung-sinh.model';
import { BlcGiayChungSinhService } from './blc-giay-chung-sinh.service';
import { BlcGiayChungSinhComponent } from './blc-giay-chung-sinh.component';
import { BlcGiayChungSinhDetailComponent } from './blc-giay-chung-sinh-detail.component';
import { BlcGiayChungSinhUpdateComponent } from './blc-giay-chung-sinh-update.component';
import { BlcGiayChungSinhDeletePopupComponent } from './blc-giay-chung-sinh-delete-dialog.component';
import { IBlcGiayChungSinh } from 'app/shared/model/blc-giay-chung-sinh.model';

@Injectable({ providedIn: 'root' })
export class BlcGiayChungSinhResolve implements Resolve<IBlcGiayChungSinh> {
  constructor(private service: BlcGiayChungSinhService) {}

  resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<IBlcGiayChungSinh> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        filter((response: HttpResponse<BlcGiayChungSinh>) => response.ok),
        map((blcGiayChungSinh: HttpResponse<BlcGiayChungSinh>) => blcGiayChungSinh.body)
      );
    }
    return of(new BlcGiayChungSinh());
  }
}

export const blcGiayChungSinhRoute: Routes = [
  {
    path: '',
    component: BlcGiayChungSinhComponent,
    resolve: {
      pagingParams: JhiResolvePagingParams
    },
    data: {
      authorities: ['ROLE_USER'],
      defaultSort: 'id,asc',
      pageTitle: 'BlcGiayChungSinhs'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: BlcGiayChungSinhDetailComponent,
    resolve: {
      blcGiayChungSinh: BlcGiayChungSinhResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'BlcGiayChungSinhs'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: BlcGiayChungSinhUpdateComponent,
    resolve: {
      blcGiayChungSinh: BlcGiayChungSinhResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'BlcGiayChungSinhs'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: BlcGiayChungSinhUpdateComponent,
    resolve: {
      blcGiayChungSinh: BlcGiayChungSinhResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'BlcGiayChungSinhs'
    },
    canActivate: [UserRouteAccessService]
  }
];

export const blcGiayChungSinhPopupRoute: Routes = [
  {
    path: ':id/delete',
    component: BlcGiayChungSinhDeletePopupComponent,
    resolve: {
      blcGiayChungSinh: BlcGiayChungSinhResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'BlcGiayChungSinhs'
    },
    canActivate: [UserRouteAccessService],
    outlet: 'popup'
  }
];
