import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IDonViHanhChinh } from 'app/shared/model/don-vi-hanh-chinh.model';

type EntityResponseType = HttpResponse<IDonViHanhChinh>;
type EntityArrayResponseType = HttpResponse<IDonViHanhChinh[]>;

@Injectable({ providedIn: 'root' })
export class DonViHanhChinhService {
  public resourceUrl = SERVER_API_URL + 'api/don-vi-hanh-chinhs';

  constructor(protected http: HttpClient) {}

  create(donViHanhChinh: IDonViHanhChinh): Observable<EntityResponseType> {
    return this.http.post<IDonViHanhChinh>(this.resourceUrl, donViHanhChinh, { observe: 'response' });
  }

  update(donViHanhChinh: IDonViHanhChinh): Observable<EntityResponseType> {
    return this.http.put<IDonViHanhChinh>(this.resourceUrl, donViHanhChinh, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IDonViHanhChinh>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IDonViHanhChinh[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<any>> {
    return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
