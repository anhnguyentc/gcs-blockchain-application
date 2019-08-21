import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { DanToc } from 'app/shared/model/dan-toc.model';
import { DanTocService } from './dan-toc.service';
import { DanTocComponent } from './dan-toc.component';
import { DanTocDetailComponent } from './dan-toc-detail.component';
import { DanTocUpdateComponent } from './dan-toc-update.component';
import { DanTocDeletePopupComponent } from './dan-toc-delete-dialog.component';
import { IDanToc } from 'app/shared/model/dan-toc.model';

@Injectable({ providedIn: 'root' })
export class DanTocResolve implements Resolve<IDanToc> {
  constructor(private service: DanTocService) {}

  resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<IDanToc> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        filter((response: HttpResponse<DanToc>) => response.ok),
        map((danToc: HttpResponse<DanToc>) => danToc.body)
      );
    }
    return of(new DanToc());
  }
}

export const danTocRoute: Routes = [
  {
    path: '',
    component: DanTocComponent,
    resolve: {
      pagingParams: JhiResolvePagingParams
    },
    data: {
      authorities: ['ROLE_USER'],
      defaultSort: 'id,asc',
      pageTitle: 'DanTocs'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: DanTocDetailComponent,
    resolve: {
      danToc: DanTocResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'DanTocs'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: DanTocUpdateComponent,
    resolve: {
      danToc: DanTocResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'DanTocs'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: DanTocUpdateComponent,
    resolve: {
      danToc: DanTocResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'DanTocs'
    },
    canActivate: [UserRouteAccessService]
  }
];

export const danTocPopupRoute: Routes = [
  {
    path: ':id/delete',
    component: DanTocDeletePopupComponent,
    resolve: {
      danToc: DanTocResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'DanTocs'
    },
    canActivate: [UserRouteAccessService],
    outlet: 'popup'
  }
];
