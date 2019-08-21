import { Component, OnInit } from '@angular/core';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { JhiAlertService } from 'ng-jhipster';
import { IBlcGiayChungSinh, BlcGiayChungSinh } from 'app/shared/model/blc-giay-chung-sinh.model';
import { BlcGiayChungSinhService } from './blc-giay-chung-sinh.service';
import { IBlcAPILog } from 'app/shared/model/blc-api-log.model';
import { BlcAPILogService } from 'app/entities/blc-api-log';
import { IDanToc } from 'app/shared/model/dan-toc.model';
import { DanTocService } from 'app/entities/dan-toc';
import { IDonViHanhChinh } from 'app/shared/model/don-vi-hanh-chinh.model';
import { DonViHanhChinhService } from 'app/entities/don-vi-hanh-chinh';
import { IBenhVien } from 'app/shared/model/benh-vien.model';
import { BenhVienService } from 'app/entities/benh-vien';

@Component({
  selector: 'jhi-blc-giay-chung-sinh-update',
  templateUrl: './blc-giay-chung-sinh-update.component.html'
})
export class BlcGiayChungSinhUpdateComponent implements OnInit {
  isSaving: boolean;

  blcapilogs: IBlcAPILog[];

  dantocs: IDanToc[];

  donvihanhchinhs: IDonViHanhChinh[];

  benhviens: IBenhVien[];

  editForm = this.fb.group({
    id: [],
    uuid: [null, [Validators.required]],
    createdDate: [],
    tenMeNguoiNuoiDuong: [],
    namSinh: [],
    diaChiThuongTru: [],
    soCMNDPassport: [],
    danToc: [],
    gioSinh: [],
    ngayThangNam: [],
    noiSinh: [],
    soLanSinh: [],
    soConHienSong: [],
    soConTrongLanSinh: [],
    gioiTinh: [],
    canNang: [],
    hienTrang: [],
    nguoiDoDe: [],
    tenDuDinh: [],
    qrCodeImage: [],
    statusApp: [],
    userApprove: [],
    userCreate: [],
    addressGCS: [],
    typeGCS: [],
    so: [],
    quyenSo: [],
    emailNDK: [],
    modifiedDate: [],
    idBenhVien: [],
    tenBenhVien: [],
    idUserCreate: [],
    publicKey: [],
    codeSoft: [],
    ngayThangNamCap: [],
    noiCap: [],
    hoTenCha: [],
    pdfFile: [],
    status: [],
    blcAPILog: []
  });

  constructor(
    protected jhiAlertService: JhiAlertService,
    protected blcGiayChungSinhService: BlcGiayChungSinhService,
    protected blcAPILogService: BlcAPILogService,
    protected danTocService: DanTocService,
    protected donViHanhChinhService: DonViHanhChinhService,
    protected benhVienService: BenhVienService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit() {
    this.isSaving = false;
    this.activatedRoute.data.subscribe(({ blcGiayChungSinh }) => {
      this.updateForm(blcGiayChungSinh);
    });
    this.blcAPILogService
      .query({ 'blcGiayChungSinhId.specified': 'false' })
      .pipe(
        filter((mayBeOk: HttpResponse<IBlcAPILog[]>) => mayBeOk.ok),
        map((response: HttpResponse<IBlcAPILog[]>) => response.body)
      )
      .subscribe(
        (res: IBlcAPILog[]) => {
          if (!this.editForm.get('blcAPILog').value || !this.editForm.get('blcAPILog').value.id) {
            this.blcapilogs = res;
          } else {
            this.blcAPILogService
              .find(this.editForm.get('blcAPILog').value.id)
              .pipe(
                filter((subResMayBeOk: HttpResponse<IBlcAPILog>) => subResMayBeOk.ok),
                map((subResponse: HttpResponse<IBlcAPILog>) => subResponse.body)
              )
              .subscribe(
                (subRes: IBlcAPILog) => (this.blcapilogs = [subRes].concat(res)),
                (subRes: HttpErrorResponse) => this.onError(subRes.message)
              );
          }
        },
        (res: HttpErrorResponse) => this.onError(res.message)
      );
    this.danTocService
      .query()
      .pipe(
        filter((mayBeOk: HttpResponse<IDanToc[]>) => mayBeOk.ok),
        map((response: HttpResponse<IDanToc[]>) => response.body)
      )
      .subscribe((res: IDanToc[]) => (this.dantocs = res), (res: HttpErrorResponse) => this.onError(res.message));
    this.donViHanhChinhService
      .query()
      .pipe(
        filter((mayBeOk: HttpResponse<IDonViHanhChinh[]>) => mayBeOk.ok),
        map((response: HttpResponse<IDonViHanhChinh[]>) => response.body)
      )
      .subscribe((res: IDonViHanhChinh[]) => (this.donvihanhchinhs = res), (res: HttpErrorResponse) => this.onError(res.message));
    this.benhVienService
      .query()
      .pipe(
        filter((mayBeOk: HttpResponse<IBenhVien[]>) => mayBeOk.ok),
        map((response: HttpResponse<IBenhVien[]>) => response.body)
      )
      .subscribe((res: IBenhVien[]) => (this.benhviens = res), (res: HttpErrorResponse) => this.onError(res.message));
  }

  updateForm(blcGiayChungSinh: IBlcGiayChungSinh) {
    this.editForm.patchValue({
      id: blcGiayChungSinh.id,
      uuid: blcGiayChungSinh.uuid,
      createdDate: blcGiayChungSinh.createdDate,
      tenMeNguoiNuoiDuong: blcGiayChungSinh.tenMeNguoiNuoiDuong,
      namSinh: blcGiayChungSinh.namSinh,
      diaChiThuongTru: blcGiayChungSinh.diaChiThuongTru,
      soCMNDPassport: blcGiayChungSinh.soCMNDPassport,
      danToc: blcGiayChungSinh.danToc,
      gioSinh: blcGiayChungSinh.gioSinh,
      ngayThangNam: blcGiayChungSinh.ngayThangNam,
      noiSinh: blcGiayChungSinh.noiSinh,
      soLanSinh: blcGiayChungSinh.soLanSinh,
      soConHienSong: blcGiayChungSinh.soConHienSong,
      soConTrongLanSinh: blcGiayChungSinh.soConTrongLanSinh,
      gioiTinh: blcGiayChungSinh.gioiTinh,
      canNang: blcGiayChungSinh.canNang,
      hienTrang: blcGiayChungSinh.hienTrang,
      nguoiDoDe: blcGiayChungSinh.nguoiDoDe,
      tenDuDinh: blcGiayChungSinh.tenDuDinh,
      qrCodeImage: blcGiayChungSinh.qrCodeImage,
      statusApp: blcGiayChungSinh.statusApp,
      userApprove: blcGiayChungSinh.userApprove,
      userCreate: blcGiayChungSinh.userCreate,
      addressGCS: blcGiayChungSinh.addressGCS,
      typeGCS: blcGiayChungSinh.typeGCS,
      so: blcGiayChungSinh.so,
      quyenSo: blcGiayChungSinh.quyenSo,
      emailNDK: blcGiayChungSinh.emailNDK,
      modifiedDate: blcGiayChungSinh.modifiedDate,
      idBenhVien: blcGiayChungSinh.idBenhVien,
      tenBenhVien: blcGiayChungSinh.tenBenhVien,
      idUserCreate: blcGiayChungSinh.idUserCreate,
      publicKey: blcGiayChungSinh.publicKey,
      codeSoft: blcGiayChungSinh.codeSoft,
      ngayThangNamCap: blcGiayChungSinh.ngayThangNamCap,
      noiCap: blcGiayChungSinh.noiCap,
      hoTenCha: blcGiayChungSinh.hoTenCha,
      pdfFile: blcGiayChungSinh.pdfFile,
      status: blcGiayChungSinh.status,
      blcAPILog: blcGiayChungSinh.blcAPILog
    });
  }

  previousState() {
    window.history.back();
  }

  save() {
    this.isSaving = true;
    const blcGiayChungSinh = this.createFromForm();
    if (blcGiayChungSinh.id !== undefined) {
      this.subscribeToSaveResponse(this.blcGiayChungSinhService.update(blcGiayChungSinh));
    } else {
      this.subscribeToSaveResponse(this.blcGiayChungSinhService.create(blcGiayChungSinh));
    }
  }

  private createFromForm(): IBlcGiayChungSinh {
    return {
      ...new BlcGiayChungSinh(),
      id: this.editForm.get(['id']).value,
      uuid: this.editForm.get(['uuid']).value,
      createdDate: this.editForm.get(['createdDate']).value,
      tenMeNguoiNuoiDuong: this.editForm.get(['tenMeNguoiNuoiDuong']).value,
      namSinh: this.editForm.get(['namSinh']).value,
      diaChiThuongTru: this.editForm.get(['diaChiThuongTru']).value,
      soCMNDPassport: this.editForm.get(['soCMNDPassport']).value,
      danToc: this.editForm.get(['danToc']).value,
      gioSinh: this.editForm.get(['gioSinh']).value,
      ngayThangNam: this.editForm.get(['ngayThangNam']).value,
      noiSinh: this.editForm.get(['noiSinh']).value,
      soLanSinh: this.editForm.get(['soLanSinh']).value,
      soConHienSong: this.editForm.get(['soConHienSong']).value,
      soConTrongLanSinh: this.editForm.get(['soConTrongLanSinh']).value,
      gioiTinh: this.editForm.get(['gioiTinh']).value,
      canNang: this.editForm.get(['canNang']).value,
      hienTrang: this.editForm.get(['hienTrang']).value,
      nguoiDoDe: this.editForm.get(['nguoiDoDe']).value,
      tenDuDinh: this.editForm.get(['tenDuDinh']).value,
      qrCodeImage: this.editForm.get(['qrCodeImage']).value,
      statusApp: this.editForm.get(['statusApp']).value,
      userApprove: this.editForm.get(['userApprove']).value,
      userCreate: this.editForm.get(['userCreate']).value,
      addressGCS: this.editForm.get(['addressGCS']).value,
      typeGCS: this.editForm.get(['typeGCS']).value,
      so: this.editForm.get(['so']).value,
      quyenSo: this.editForm.get(['quyenSo']).value,
      emailNDK: this.editForm.get(['emailNDK']).value,
      modifiedDate: this.editForm.get(['modifiedDate']).value,
      idBenhVien: this.editForm.get(['idBenhVien']).value,
      tenBenhVien: this.editForm.get(['tenBenhVien']).value,
      idUserCreate: this.editForm.get(['idUserCreate']).value,
      publicKey: this.editForm.get(['publicKey']).value,
      codeSoft: this.editForm.get(['codeSoft']).value,
      ngayThangNamCap: this.editForm.get(['ngayThangNamCap']).value,
      noiCap: this.editForm.get(['noiCap']).value,
      hoTenCha: this.editForm.get(['hoTenCha']).value,
      pdfFile: this.editForm.get(['pdfFile']).value,
      status: this.editForm.get(['status']).value,
      blcAPILog: this.editForm.get(['blcAPILog']).value
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IBlcGiayChungSinh>>) {
    result.subscribe(() => this.onSaveSuccess(), () => this.onSaveError());
  }

  protected onSaveSuccess() {
    this.isSaving = false;
    this.previousState();
  }

  protected onSaveError() {
    this.isSaving = false;
  }
  protected onError(errorMessage: string) {
    this.jhiAlertService.error(errorMessage, null, null);
  }

  trackBlcAPILogById(index: number, item: IBlcAPILog) {
    return item.id;
  }

  trackDanTocById(index: number, item: IDanToc) {
    return item.id;
  }

  trackDonViHanhChinhById(index: number, item: IDonViHanhChinh) {
    return item.id;
  }

  trackBenhVienById(index: number, item: IBenhVien) {
    return item.id;
  }
}
