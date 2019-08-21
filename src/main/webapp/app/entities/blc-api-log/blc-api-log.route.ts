import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { BlcAPILog } from 'app/shared/model/blc-api-log.model';
import { BlcAPILogService } from './blc-api-log.service';
import { BlcAPILogComponent } from './blc-api-log.component';
import { BlcAPILogDetailComponent } from './blc-api-log-detail.component';
import { BlcAPILogUpdateComponent } from './blc-api-log-update.component';
import { BlcAPILogDeletePopupComponent } from './blc-api-log-delete-dialog.component';
import { IBlcAPILog } from 'app/shared/model/blc-api-log.model';

@Injectable({ providedIn: 'root' })
export class BlcAPILogResolve implements Resolve<IBlcAPILog> {
  constructor(private service: BlcAPILogService) {}

  resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<IBlcAPILog> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        filter((response: HttpResponse<BlcAPILog>) => response.ok),
        map((blcAPILog: HttpResponse<BlcAPILog>) => blcAPILog.body)
      );
    }
    return of(new BlcAPILog());
  }
}

export const blcAPILogRoute: Routes = [
  {
    path: '',
    component: BlcAPILogComponent,
    resolve: {
      pagingParams: JhiResolvePagingParams
    },
    data: {
      authorities: ['ROLE_USER'],
      defaultSort: 'id,asc',
      pageTitle: 'BlcAPILogs'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: BlcAPILogDetailComponent,
    resolve: {
      blcAPILog: BlcAPILogResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'BlcAPILogs'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: BlcAPILogUpdateComponent,
    resolve: {
      blcAPILog: BlcAPILogResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'BlcAPILogs'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: BlcAPILogUpdateComponent,
    resolve: {
      blcAPILog: BlcAPILogResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'BlcAPILogs'
    },
    canActivate: [UserRouteAccessService]
  }
];

export const blcAPILogPopupRoute: Routes = [
  {
    path: ':id/delete',
    component: BlcAPILogDeletePopupComponent,
    resolve: {
      blcAPILog: BlcAPILogResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'BlcAPILogs'
    },
    canActivate: [UserRouteAccessService],
    outlet: 'popup'
  }
];
