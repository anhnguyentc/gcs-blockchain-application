/* tslint:disable max-line-length */
import { TestBed, getTestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { of } from 'rxjs';
import { take, map } from 'rxjs/operators';
import * as moment from 'moment';
import { DATE_FORMAT } from 'app/shared/constants/input.constants';
import { BlcGiayKhamSucKhoeService } from 'app/entities/blc-giay-kham-suc-khoe/blc-giay-kham-suc-khoe.service';
import { IBlcGiayKhamSucKhoe, BlcGiayKhamSucKhoe } from 'app/shared/model/blc-giay-kham-suc-khoe.model';

describe('Service Tests', () => {
  describe('BlcGiayKhamSucKhoe Service', () => {
    let injector: TestBed;
    let service: BlcGiayKhamSucKhoeService;
    let httpMock: HttpTestingController;
    let elemDefault: IBlcGiayKhamSucKhoe;
    let expectedResult;
    let currentDate: moment.Moment;
    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule]
      });
      expectedResult = {};
      injector = getTestBed();
      service = injector.get(BlcGiayKhamSucKhoeService);
      httpMock = injector.get(HttpTestingController);
      currentDate = moment();

      elemDefault = new BlcGiayKhamSucKhoe(
        0,
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        0,
        'AAAAAAA',
        currentDate,
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
        const returnedFromService = Object.assign(
          {
            ngayCap: currentDate.format(DATE_FORMAT)
          },
          elemDefault
        );
        service
          .find(123)
          .pipe(take(1))
          .subscribe(resp => (expectedResult = resp));

        const req = httpMock.expectOne({ method: 'GET' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject({ body: elemDefault });
      });

      it('should create a BlcGiayKhamSucKhoe', async () => {
        const returnedFromService = Object.assign(
          {
            id: 0,
            ngayCap: currentDate.format(DATE_FORMAT)
          },
          elemDefault
        );
        const expected = Object.assign(
          {
            ngayCap: currentDate
          },
          returnedFromService
        );
        service
          .create(new BlcGiayKhamSucKhoe(null))
          .pipe(take(1))
          .subscribe(resp => (expectedResult = resp));
        const req = httpMock.expectOne({ method: 'POST' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject({ body: expected });
      });

      it('should update a BlcGiayKhamSucKhoe', async () => {
        const returnedFromService = Object.assign(
          {
            uuid: 'BBBBBB',
            soGiayKSK: 'BBBBBB',
            hoTen: 'BBBBBB',
            gioiTinh: 'BBBBBB',
            tuoi: 1,
            soCMND: 'BBBBBB',
            ngayCap: currentDate.format(DATE_FORMAT),
            noiCap: 'BBBBBB',
            noiO: 'BBBBBB',
            idBenhVien: 'BBBBBB',
            tenBenhVien: 'BBBBBB',
            ngayKham: 'BBBBBB',
            hangBangLai: 'BBBBBB',
            ketLuan: 'BBBBBB',
            bacSyKetLuan: 'BBBBBB',
            pdfGiayKSK: 'BBBBBB',
            status: 1
          },
          elemDefault
        );

        const expected = Object.assign(
          {
            ngayCap: currentDate
          },
          returnedFromService
        );
        service
          .update(expected)
          .pipe(take(1))
          .subscribe(resp => (expectedResult = resp));
        const req = httpMock.expectOne({ method: 'PUT' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject({ body: expected });
      });

      it('should return a list of BlcGiayKhamSucKhoe', async () => {
        const returnedFromService = Object.assign(
          {
            uuid: 'BBBBBB',
            soGiayKSK: 'BBBBBB',
            hoTen: 'BBBBBB',
            gioiTinh: 'BBBBBB',
            tuoi: 1,
            soCMND: 'BBBBBB',
            ngayCap: currentDate.format(DATE_FORMAT),
            noiCap: 'BBBBBB',
            noiO: 'BBBBBB',
            idBenhVien: 'BBBBBB',
            tenBenhVien: 'BBBBBB',
            ngayKham: 'BBBBBB',
            hangBangLai: 'BBBBBB',
            ketLuan: 'BBBBBB',
            bacSyKetLuan: 'BBBBBB',
            pdfGiayKSK: 'BBBBBB',
            status: 1
          },
          elemDefault
        );
        const expected = Object.assign(
          {
            ngayCap: currentDate
          },
          returnedFromService
        );
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

      it('should delete a BlcGiayKhamSucKhoe', async () => {
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
