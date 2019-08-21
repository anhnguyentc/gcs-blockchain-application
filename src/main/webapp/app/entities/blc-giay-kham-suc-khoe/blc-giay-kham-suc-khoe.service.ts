import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_FORMAT } from 'app/shared/constants/input.constants';
import { map } from 'rxjs/operators';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IBlcGiayKhamSucKhoe } from 'app/shared/model/blc-giay-kham-suc-khoe.model';

type EntityResponseType = HttpResponse<IBlcGiayKhamSucKhoe>;
type EntityArrayResponseType = HttpResponse<IBlcGiayKhamSucKhoe[]>;

@Injectable({ providedIn: 'root' })
export class BlcGiayKhamSucKhoeService {
  public resourceUrl = SERVER_API_URL + 'api/blc-giay-kham-suc-khoes';

  constructor(protected http: HttpClient) {}

  create(blcGiayKhamSucKhoe: IBlcGiayKhamSucKhoe): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(blcGiayKhamSucKhoe);
    return this.http
      .post<IBlcGiayKhamSucKhoe>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  update(blcGiayKhamSucKhoe: IBlcGiayKhamSucKhoe): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(blcGiayKhamSucKhoe);
    return this.http
      .put<IBlcGiayKhamSucKhoe>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http
      .get<IBlcGiayKhamSucKhoe>(`${this.resourceUrl}/${id}`, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<IBlcGiayKhamSucKhoe[]>(this.resourceUrl, { params: options, observe: 'response' })
      .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
  }

  delete(id: number): Observable<HttpResponse<any>> {
    return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  protected convertDateFromClient(blcGiayKhamSucKhoe: IBlcGiayKhamSucKhoe): IBlcGiayKhamSucKhoe {
    const copy: IBlcGiayKhamSucKhoe = Object.assign({}, blcGiayKhamSucKhoe, {
      ngayCap:
        blcGiayKhamSucKhoe.ngayCap != null && blcGiayKhamSucKhoe.ngayCap.isValid() ? blcGiayKhamSucKhoe.ngayCap.format(DATE_FORMAT) : null
    });
    return copy;
  }

  protected convertDateFromServer(res: EntityResponseType): EntityResponseType {
    if (res.body) {
      res.body.ngayCap = res.body.ngayCap != null ? moment(res.body.ngayCap) : null;
    }
    return res;
  }

  protected convertDateArrayFromServer(res: EntityArrayResponseType): EntityArrayResponseType {
    if (res.body) {
      res.body.forEach((blcGiayKhamSucKhoe: IBlcGiayKhamSucKhoe) => {
        blcGiayKhamSucKhoe.ngayCap = blcGiayKhamSucKhoe.ngayCap != null ? moment(blcGiayKhamSucKhoe.ngayCap) : null;
      });
    }
    return res;
  }
}
