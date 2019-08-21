import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IDanToc } from 'app/shared/model/dan-toc.model';

type EntityResponseType = HttpResponse<IDanToc>;
type EntityArrayResponseType = HttpResponse<IDanToc[]>;

@Injectable({ providedIn: 'root' })
export class DanTocService {
  public resourceUrl = SERVER_API_URL + 'api/dan-tocs';

  constructor(protected http: HttpClient) {}

  create(danToc: IDanToc): Observable<EntityResponseType> {
    return this.http.post<IDanToc>(this.resourceUrl, danToc, { observe: 'response' });
  }

  update(danToc: IDanToc): Observable<EntityResponseType> {
    return this.http.put<IDanToc>(this.resourceUrl, danToc, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IDanToc>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IDanToc[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<any>> {
    return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
