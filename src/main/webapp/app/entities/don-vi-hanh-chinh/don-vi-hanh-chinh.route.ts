import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { DonViHanhChinh } from 'app/shared/model/don-vi-hanh-chinh.model';
import { DonViHanhChinhService } from './don-vi-hanh-chinh.service';
import { DonViHanhChinhComponent } from './don-vi-hanh-chinh.component';
import { DonViHanhChinhDetailComponent } from './don-vi-hanh-chinh-detail.component';
import { DonViHanhChinhUpdateComponent } from './don-vi-hanh-chinh-update.component';
import { DonViHanhChinhDeletePopupComponent } from './don-vi-hanh-chinh-delete-dialog.component';
import { IDonViHanhChinh } from 'app/shared/model/don-vi-hanh-chinh.model';

@Injectable({ providedIn: 'root' })
export class DonViHanhChinhResolve implements Resolve<IDonViHanhChinh> {
  constructor(private service: DonViHanhChinhService) {}

  resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<IDonViHanhChinh> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        filter((response: HttpResponse<DonViHanhChinh>) => response.ok),
        map((donViHanhChinh: HttpResponse<DonViHanhChinh>) => donViHanhChinh.body)
      );
    }
    return of(new DonViHanhChinh());
  }
}

export const donViHanhChinhRoute: Routes = [
  {
    path: '',
    component: DonViHanhChinhComponent,
    resolve: {
      pagingParams: JhiResolvePagingParams
    },
    data: {
      authorities: ['ROLE_USER'],
      defaultSort: 'id,asc',
      pageTitle: 'DonViHanhChinhs'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: DonViHanhChinhDetailComponent,
    resolve: {
      donViHanhChinh: DonViHanhChinhResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'DonViHanhChinhs'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: DonViHanhChinhUpdateComponent,
    resolve: {
      donViHanhChinh: DonViHanhChinhResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'DonViHanhChinhs'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: DonViHanhChinhUpdateComponent,
    resolve: {
      donViHanhChinh: DonViHanhChinhResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'DonViHanhChinhs'
    },
    canActivate: [UserRouteAccessService]
  }
];

export const donViHanhChinhPopupRoute: Routes = [
  {
    path: ':id/delete',
    component: DonViHanhChinhDeletePopupComponent,
    resolve: {
      donViHanhChinh: DonViHanhChinhResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'DonViHanhChinhs'
    },
    canActivate: [UserRouteAccessService],
    outlet: 'popup'
  }
];
