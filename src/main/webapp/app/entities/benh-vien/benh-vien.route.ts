import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { BenhVien } from 'app/shared/model/benh-vien.model';
import { BenhVienService } from './benh-vien.service';
import { BenhVienComponent } from './benh-vien.component';
import { BenhVienDetailComponent } from './benh-vien-detail.component';
import { BenhVienUpdateComponent } from './benh-vien-update.component';
import { BenhVienDeletePopupComponent } from './benh-vien-delete-dialog.component';
import { IBenhVien } from 'app/shared/model/benh-vien.model';

@Injectable({ providedIn: 'root' })
export class BenhVienResolve implements Resolve<IBenhVien> {
  constructor(private service: BenhVienService) {}

  resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<IBenhVien> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        filter((response: HttpResponse<BenhVien>) => response.ok),
        map((benhVien: HttpResponse<BenhVien>) => benhVien.body)
      );
    }
    return of(new BenhVien());
  }
}

export const benhVienRoute: Routes = [
  {
    path: '',
    component: BenhVienComponent,
    resolve: {
      pagingParams: JhiResolvePagingParams
    },
    data: {
      authorities: ['ROLE_USER'],
      defaultSort: 'id,asc',
      pageTitle: 'BenhViens'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: BenhVienDetailComponent,
    resolve: {
      benhVien: BenhVienResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'BenhViens'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: BenhVienUpdateComponent,
    resolve: {
      benhVien: BenhVienResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'BenhViens'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: BenhVienUpdateComponent,
    resolve: {
      benhVien: BenhVienResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'BenhViens'
    },
    canActivate: [UserRouteAccessService]
  }
];

export const benhVienPopupRoute: Routes = [
  {
    path: ':id/delete',
    component: BenhVienDeletePopupComponent,
    resolve: {
      benhVien: BenhVienResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'BenhViens'
    },
    canActivate: [UserRouteAccessService],
    outlet: 'popup'
  }
];
