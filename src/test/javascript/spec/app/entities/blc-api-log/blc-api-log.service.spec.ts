/* tslint:disable max-line-length */
import { TestBed, getTestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { of } from 'rxjs';
import { take, map } from 'rxjs/operators';
import * as moment from 'moment';
import { DATE_FORMAT } from 'app/shared/constants/input.constants';
import { BlcAPILogService } from 'app/entities/blc-api-log/blc-api-log.service';
import { IBlcAPILog, BlcAPILog } from 'app/shared/model/blc-api-log.model';

describe('Service Tests', () => {
  describe('BlcAPILog Service', () => {
    let injector: TestBed;
    let service: BlcAPILogService;
    let httpMock: HttpTestingController;
    let elemDefault: IBlcAPILog;
    let expectedResult;
    let currentDate: moment.Moment;
    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule]
      });
      expectedResult = {};
      injector = getTestBed();
      service = injector.get(BlcAPILogService);
      httpMock = injector.get(HttpTestingController);
      currentDate = moment();

      elemDefault = new BlcAPILog(
        0,
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        currentDate,
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        0,
        0
      );
    });

    describe('Service methods', () => {
      it('should find an element', async () => {
        const returnedFromService = Object.assign(
          {
            loadedTime: currentDate.format(DATE_FORMAT)
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

      it('should create a BlcAPILog', async () => {
        const returnedFromService = Object.assign(
          {
            id: 0,
            loadedTime: currentDate.format(DATE_FORMAT)
          },
          elemDefault
        );
        const expected = Object.assign(
          {
            loadedTime: currentDate
          },
          returnedFromService
        );
        service
          .create(new BlcAPILog(null))
          .pipe(take(1))
          .subscribe(resp => (expectedResult = resp));
        const req = httpMock.expectOne({ method: 'POST' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject({ body: expected });
      });

      it('should update a BlcAPILog', async () => {
        const returnedFromService = Object.assign(
          {
            uuid: 'BBBBBB',
            status: 'BBBBBB',
            messageStatus: 'BBBBBB',
            methodName: 'BBBBBB',
            soGCS: 'BBBBBB',
            soSOGCS: 'BBBBBB',
            requestContent: 'BBBBBB',
            errorMessage: 'BBBBBB',
            loadedTime: currentDate.format(DATE_FORMAT),
            ipAddress: 'BBBBBB',
            idBenhvien: 'BBBBBB',
            tenBenhvien: 'BBBBBB',
            processTime: 1,
            responseTime: 1
          },
          elemDefault
        );

        const expected = Object.assign(
          {
            loadedTime: currentDate
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

      it('should return a list of BlcAPILog', async () => {
        const returnedFromService = Object.assign(
          {
            uuid: 'BBBBBB',
            status: 'BBBBBB',
            messageStatus: 'BBBBBB',
            methodName: 'BBBBBB',
            soGCS: 'BBBBBB',
            soSOGCS: 'BBBBBB',
            requestContent: 'BBBBBB',
            errorMessage: 'BBBBBB',
            loadedTime: currentDate.format(DATE_FORMAT),
            ipAddress: 'BBBBBB',
            idBenhvien: 'BBBBBB',
            tenBenhvien: 'BBBBBB',
            processTime: 1,
            responseTime: 1
          },
          elemDefault
        );
        const expected = Object.assign(
          {
            loadedTime: currentDate
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

      it('should delete a BlcAPILog', async () => {
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
