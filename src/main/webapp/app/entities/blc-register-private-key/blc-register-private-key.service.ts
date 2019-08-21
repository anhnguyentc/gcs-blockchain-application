import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IBlcRegisterPrivateKey } from 'app/shared/model/blc-register-private-key.model';

type EntityResponseType = HttpResponse<IBlcRegisterPrivateKey>;
type EntityArrayResponseType = HttpResponse<IBlcRegisterPrivateKey[]>;

@Injectable({ providedIn: 'root' })
export class BlcRegisterPrivateKeyService {
  public resourceUrl = SERVER_API_URL + 'api/blc-register-private-keys';

  constructor(protected http: HttpClient) {}

  create(blcRegisterPrivateKey: IBlcRegisterPrivateKey): Observable<EntityResponseType> {
    return this.http.post<IBlcRegisterPrivateKey>(this.resourceUrl, blcRegisterPrivateKey, { observe: 'response' });
  }

  update(blcRegisterPrivateKey: IBlcRegisterPrivateKey): Observable<EntityResponseType> {
    return this.http.put<IBlcRegisterPrivateKey>(this.resourceUrl, blcRegisterPrivateKey, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IBlcRegisterPrivateKey>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IBlcRegisterPrivateKey[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<any>> {
    return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
