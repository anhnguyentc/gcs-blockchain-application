/* tslint:disable max-line-length */
import { TestBed, getTestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { of } from 'rxjs';
import { take, map } from 'rxjs/operators';
import { BlcRegisterPrivateKeyService } from 'app/entities/blc-register-private-key/blc-register-private-key.service';
import { IBlcRegisterPrivateKey, BlcRegisterPrivateKey } from 'app/shared/model/blc-register-private-key.model';

describe('Service Tests', () => {
  describe('BlcRegisterPrivateKey Service', () => {
    let injector: TestBed;
    let service: BlcRegisterPrivateKeyService;
    let httpMock: HttpTestingController;
    let elemDefault: IBlcRegisterPrivateKey;
    let expectedResult;
    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule]
      });
      expectedResult = {};
      injector = getTestBed();
      service = injector.get(BlcRegisterPrivateKeyService);
      httpMock = injector.get(HttpTestingController);

      elemDefault = new BlcRegisterPrivateKey(
        0,
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        0
      );
    });

    describe('Service methods', () => {
      it('should find an element', async () => {
        const returnedFromService = Object.assign({}, elemDefault);
        service
          .find(123)
          .pipe(take(1))
          .subscribe(resp => (expectedResult = resp));

        const req = httpMock.expectOne({ method: 'GET' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject({ body: elemDefault });
      });

      it('should create a BlcRegisterPrivateKey', async () => {
        const returnedFromService = Object.assign(
          {
            id: 0
          },
          elemDefault
        );
        const expected = Object.assign({}, returnedFromService);
        service
          .create(new BlcRegisterPrivateKey(null))
          .pipe(take(1))
          .subscribe(resp => (expectedResult = resp));
        const req = httpMock.expectOne({ method: 'POST' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject({ body: expected });
      });

      it('should update a BlcRegisterPrivateKey', async () => {
        const returnedFromService = Object.assign(
          {
            idBenhVien: 'BBBBBB',
            tenBenhVien: 'BBBBBB',
            uuidAcccount: 'BBBBBB',
            uuidGCSDB: 'BBBBBB',
            idUserCreate: 'BBBBBB',
            codeSoft: 'BBBBBB',
            publicKey: 'BBBBBB',
            addressKey: 'BBBBBB',
            createdDate: 'BBBBBB',
            modifiedDate: 'BBBBBB',
            status: 1
          },
          elemDefault
        );

        const expected = Object.assign({}, returnedFromService);
        service
          .update(expected)
          .pipe(take(1))
          .subscribe(resp => (expectedResult = resp));
        const req = httpMock.expectOne({ method: 'PUT' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject({ body: expected });
      });

      it('should return a list of BlcRegisterPrivateKey', async () => {
        const returnedFromService = Object.assign(
          {
            idBenhVien: 'BBBBBB',
            tenBenhVien: 'BBBBBB',
            uuidAcccount: 'BBBBBB',
            uuidGCSDB: 'BBBBBB',
            idUserCreate: 'BBBBBB',
            codeSoft: 'BBBBBB',
            publicKey: 'BBBBBB',
            addressKey: 'BBBBBB',
            createdDate: 'BBBBBB',
            modifiedDate: 'BBBBBB',
            status: 1
          },
          elemDefault
        );
        const expected = Object.assign({}, returnedFromService);
        service
          .query(expected)
          .pipe(
            take(1),
            map(resp => resp.body)
          )
          .subscribe(body => (expectedResult = body));
        const req = httpMock.expectOne({ method: 'GET' });
        req.flush([returnedFromService]);
        httpMock.verify();
        expect(expectedResult).toContainEqual(expected);
      });

      it('should delete a BlcRegisterPrivateKey', async () => {
        const rxPromise = service.delete(123).subscribe(resp => (expectedResult = resp.ok));

        const req = httpMock.expectOne({ method: 'DELETE' });
        req.flush({ status: 200 });
        expect(expectedResult);
      });
    });

    afterEach(() => {
      httpMock.verify();
    });
  });
});
