import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IBenhVien } from 'app/shared/model/benh-vien.model';

type EntityResponseType = HttpResponse<IBenhVien>;
type EntityArrayResponseType = HttpResponse<IBenhVien[]>;

@Injectable({ providedIn: 'root' })
export class BenhVienService {
  public resourceUrl = SERVER_API_URL + 'api/benh-viens';

  constructor(protected http: HttpClient) {}

  create(benhVien: IBenhVien): Observable<EntityResponseType> {
    return this.http.post<IBenhVien>(this.resourceUrl, benhVien, { observe: 'response' });
  }

  update(benhVien: IBenhVien): Observable<EntityResponseType> {
    return this.http.put<IBenhVien>(this.resourceUrl, benhVien, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IBenhVien>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IBenhVien[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<any>> {
    return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
