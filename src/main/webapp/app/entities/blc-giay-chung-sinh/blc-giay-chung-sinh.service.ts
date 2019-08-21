import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IBlcGiayChungSinh } from 'app/shared/model/blc-giay-chung-sinh.model';

type EntityResponseType = HttpResponse<IBlcGiayChungSinh>;
type EntityArrayResponseType = HttpResponse<IBlcGiayChungSinh[]>;

@Injectable({ providedIn: 'root' })
export class BlcGiayChungSinhService {
  public resourceUrl = SERVER_API_URL + 'api/blc-giay-chung-sinhs';

  constructor(protected http: HttpClient) {}

  create(blcGiayChungSinh: IBlcGiayChungSinh): Observable<EntityResponseType> {
    return this.http.post<IBlcGiayChungSinh>(this.resourceUrl, blcGiayChungSinh, { observe: 'response' });
  }

  update(blcGiayChungSinh: IBlcGiayChungSinh): Observable<EntityResponseType> {
    return this.http.put<IBlcGiayChungSinh>(this.resourceUrl, blcGiayChungSinh, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IBlcGiayChungSinh>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IBlcGiayChungSinh[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<any>> {
    return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
