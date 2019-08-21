import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_FORMAT } from 'app/shared/constants/input.constants';
import { map } from 'rxjs/operators';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IBlcAPILog } from 'app/shared/model/blc-api-log.model';

type EntityResponseType = HttpResponse<IBlcAPILog>;
type EntityArrayResponseType = HttpResponse<IBlcAPILog[]>;

@Injectable({ providedIn: 'root' })
export class BlcAPILogService {
  public resourceUrl = SERVER_API_URL + 'api/blc-api-logs';

  constructor(protected http: HttpClient) {}

  create(blcAPILog: IBlcAPILog): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(blcAPILog);
    return this.http
      .post<IBlcAPILog>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  update(blcAPILog: IBlcAPILog): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(blcAPILog);
    return this.http
      .put<IBlcAPILog>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http
      .get<IBlcAPILog>(`${this.resourceUrl}/${id}`, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<IBlcAPILog[]>(this.resourceUrl, { params: options, observe: 'response' })
      .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
  }

  delete(id: number): Observable<HttpResponse<any>> {
    return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  protected convertDateFromClient(blcAPILog: IBlcAPILog): IBlcAPILog {
    const copy: IBlcAPILog = Object.assign({}, blcAPILog, {
      loadedTime: blcAPILog.loadedTime != null && blcAPILog.loadedTime.isValid() ? blcAPILog.loadedTime.format(DATE_FORMAT) : null
    });
    return copy;
  }

  protected convertDateFromServer(res: EntityResponseType): EntityResponseType {
    if (res.body) {
      res.body.loadedTime = res.body.loadedTime != null ? moment(res.body.loadedTime) : null;
    }
    return res;
  }

  protected convertDateArrayFromServer(res: EntityArrayResponseType): EntityArrayResponseType {
    if (res.body) {
      res.body.forEach((blcAPILog: IBlcAPILog) => {
        blcAPILog.loadedTime = blcAPILog.loadedTime != null ? moment(blcAPILog.loadedTime) : null;
      });
    }
    return res;
  }
}
