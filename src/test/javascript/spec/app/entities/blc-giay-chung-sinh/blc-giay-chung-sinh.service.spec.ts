/* tslint:disable max-line-length */
import { TestBed, getTestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { of } from 'rxjs';
import { take, map } from 'rxjs/operators';
import { BlcGiayChungSinhService } from 'app/entities/blc-giay-chung-sinh/blc-giay-chung-sinh.service';
import { IBlcGiayChungSinh, BlcGiayChungSinh } from 'app/shared/model/blc-giay-chung-sinh.model';

describe('Service Tests', () => {
  describe('BlcGiayChungSinh Service', () => {
    let injector: TestBed;
    let service: BlcGiayChungSinhService;
    let httpMock: HttpTestingController;
    let elemDefault: IBlcGiayChungSinh;
    let expectedResult;
    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule]
      });
      expectedResult = {};
      injector = getTestBed();
      service = injector.get(BlcGiayChungSinhService);
      httpMock = injector.get(HttpTestingController);

      elemDefault = new BlcGiayChungSinh(
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
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA'
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

      it('should create a BlcGiayChungSinh', async () => {
        const returnedFromService = Object.assign(
          {
            id: 0
          },
          elemDefault
        );
        const expected = Object.assign({}, returnedFromService);
        service
          .create(new BlcGiayChungSinh(null))
          .pipe(take(1))
          .subscribe(resp => (expectedResult = resp));
        const req = httpMock.expectOne({ method: 'POST' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject({ body: expected });
      });

      it('should update a BlcGiayChungSinh', async () => {
        const returnedFromService = Object.assign(
          {
            uuid: 'BBBBBB',
            createdDate: 'BBBBBB',
            tenMeNguoiNuoiDuong: 'BBBBBB',
            namSinh: 'BBBBBB',
            diaChiThuongTru: 'BBBBBB',
            soCMNDPassport: 'BBBBBB',
            danToc: 'BBBBBB',
            gioSinh: 'BBBBBB',
            ngayThangNam: 'BBBBBB',
            noiSinh: 'BBBBBB',
            soLanSinh: 'BBBBBB',
            soConHienSong: 'BBBBBB',
            soConTrongLanSinh: 'BBBBBB',
            gioiTinh: 'BBBBBB',
            canNang: 'BBBBBB',
            hienTrang: 'BBBBBB',
            nguoiDoDe: 'BBBBBB',
            tenDuDinh: 'BBBBBB',
            qrCodeImage: 'BBBBBB',
            statusApp: 'BBBBBB',
            userApprove: 'BBBBBB',
            userCreate: 'BBBBBB',
            addressGCS: 'BBBBBB',
            typeGCS: 'BBBBBB',
            so: 'BBBBBB',
            quyenSo: 'BBBBBB',
            emailNDK: 'BBBBBB',
            modifiedDate: 'BBBBBB',
            idBenhVien: 'BBBBBB',
            tenBenhVien: 'BBBBBB',
            idUserCreate: 'BBBBBB',
            publicKey: 'BBBBBB',
            codeSoft: 'BBBBBB',
            ngayThangNamCap: 'BBBBBB',
            noiCap: 'BBBBBB',
            hoTenCha: 'BBBBBB',
            pdfFile: 'BBBBBB',
            status: 'BBBBBB'
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

      it('should return a list of BlcGiayChungSinh', async () => {
        const returnedFromService = Object.assign(
          {
            uuid: 'BBBBBB',
            createdDate: 'BBBBBB',
            tenMeNguoiNuoiDuong: 'BBBBBB',
            namSinh: 'BBBBBB',
            diaChiThuongTru: 'BBBBBB',
            soCMNDPassport: 'BBBBBB',
            danToc: 'BBBBBB',
            gioSinh: 'BBBBBB',
            ngayThangNam: 'BBBBBB',
            noiSinh: 'BBBBBB',
            soLanSinh: 'BBBBBB',
            soConHienSong: 'BBBBBB',
            soConTrongLanSinh: 'BBBBBB',
            gioiTinh: 'BBBBBB',
            canNang: 'BBBBBB',
            hienTrang: 'BBBBBB',
            nguoiDoDe: 'BBBBBB',
            tenDuDinh: 'BBBBBB',
            qrCodeImage: 'BBBBBB',
            statusApp: 'BBBBBB',
            userApprove: 'BBBBBB',
            userCreate: 'BBBBBB',
            addressGCS: 'BBBBBB',
            typeGCS: 'BBBBBB',
            so: 'BBBBBB',
            quyenSo: 'BBBBBB',
            emailNDK: 'BBBBBB',
            modifiedDate: 'BBBBBB',
            idBenhVien: 'BBBBBB',
            tenBenhVien: 'BBBBBB',
            idUserCreate: 'BBBBBB',
            publicKey: 'BBBBBB',
            codeSoft: 'BBBBBB',
            ngayThangNamCap: 'BBBBBB',
            noiCap: 'BBBBBB',
            hoTenCha: 'BBBBBB',
            pdfFile: 'BBBBBB',
            status: 'BBBBBB'
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

      it('should delete a BlcGiayChungSinh', async () => {
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
