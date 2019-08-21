import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_FORMAT } from 'app/shared/constants/input.constants';
import { map } from 'rxjs/operators';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IAdmUserTokenLogin } from 'app/shared/model/adm-user-token-login.model';

type EntityResponseType = HttpResponse<IAdmUserTokenLogin>;
type EntityArrayResponseType = HttpResponse<IAdmUserTokenLogin[]>;

@Injectable({ providedIn: 'root' })
export class AdmUserTokenLoginService {
  public resourceUrl = SERVER_API_URL + 'api/adm-user-token-logins';

  constructor(protected http: HttpClient) {}

  create(admUserTokenLogin: IAdmUserTokenLogin): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(admUserTokenLogin);
    return this.http
      .post<IAdmUserTokenLogin>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  update(admUserTokenLogin: IAdmUserTokenLogin): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(admUserTokenLogin);
    return this.http
      .put<IAdmUserTokenLogin>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http
      .get<IAdmUserTokenLogin>(`${this.resourceUrl}/${id}`, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<IAdmUserTokenLogin[]>(this.resourceUrl, { params: options, observe: 'response' })
      .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
  }

  delete(id: number): Observable<HttpResponse<any>> {
    return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  protected convertDateFromClient(admUserTokenLogin: IAdmUserTokenLogin): IAdmUserTokenLogin {
    const copy: IAdmUserTokenLogin = Object.assign({}, admUserTokenLogin, {
      createdDate:
        admUserTokenLogin.createdDate != null && admUserTokenLogin.createdDate.isValid()
          ? admUserTokenLogin.createdDate.format(DATE_FORMAT)
          : null,
      expirationDate:
        admUserTokenLogin.expirationDate != null && admUserTokenLogin.expirationDate.isValid()
          ? admUserTokenLogin.expirationDate.format(DATE_FORMAT)
          : null
    });
    return copy;
  }

  protected convertDateFromServer(res: EntityResponseType): EntityResponseType {
    if (res.body) {
      res.body.createdDate = res.body.createdDate != null ? moment(res.body.createdDate) : null;
      res.body.expirationDate = res.body.expirationDate != null ? moment(res.body.expirationDate) : null;
    }
    return res;
  }

  protected convertDateArrayFromServer(res: EntityArrayResponseType): EntityArrayResponseType {
    if (res.body) {
      res.body.forEach((admUserTokenLogin: IAdmUserTokenLogin) => {
        admUserTokenLogin.createdDate = admUserTokenLogin.createdDate != null ? moment(admUserTokenLogin.createdDate) : null;
        admUserTokenLogin.expirationDate = admUserTokenLogin.expirationDate != null ? moment(admUserTokenLogin.expirationDate) : null;
      });
    }
    return res;
  }
}
