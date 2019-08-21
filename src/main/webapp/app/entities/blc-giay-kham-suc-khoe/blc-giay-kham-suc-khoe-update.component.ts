import { Component, OnInit } from '@angular/core';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import * as moment from 'moment';
import { JhiAlertService } from 'ng-jhipster';
import { IBlcGiayKhamSucKhoe, BlcGiayKhamSucKhoe } from 'app/shared/model/blc-giay-kham-suc-khoe.model';
import { BlcGiayKhamSucKhoeService } from './blc-giay-kham-suc-khoe.service';
import { IDonViHanhChinh } from 'app/shared/model/don-vi-hanh-chinh.model';
import { DonViHanhChinhService } from 'app/entities/don-vi-hanh-chinh';
import { IBenhVien } from 'app/shared/model/benh-vien.model';
import { BenhVienService } from 'app/entities/benh-vien';

@Component({
  selector: 'jhi-blc-giay-kham-suc-khoe-update',
  templateUrl: './blc-giay-kham-suc-khoe-update.component.html'
})
export class BlcGiayKhamSucKhoeUpdateComponent implements OnInit {
  isSaving: boolean;

  donvihanhchinhs: IDonViHanhChinh[];

  benhviens: IBenhVien[];
  ngayCapDp: any;

  editForm = this.fb.group({
    id: [],
    uuid: [null, [Validators.required]],
    soGiayKSK: [],
    hoTen: [],
    gioiTinh: [],
    tuoi: [],
    soCMND: [],
    ngayCap: [],
    noiCap: [],
    noiO: [],
    idBenhVien: [],
    tenBenhVien: [],
    ngayKham: [],
    hangBangLai: [],
    ketLuan: [],
    bacSyKetLuan: [],
    pdfGiayKSK: [],
    status: []
  });

  constructor(
    protected jhiAlertService: JhiAlertService,
    protected blcGiayKhamSucKhoeService: BlcGiayKhamSucKhoeService,
    protected donViHanhChinhService: DonViHanhChinhService,
    protected benhVienService: BenhVienService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit() {
    this.isSaving = false;
    this.activatedRoute.data.subscribe(({ blcGiayKhamSucKhoe }) => {
      this.updateForm(blcGiayKhamSucKhoe);
    });
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

  updateForm(blcGiayKhamSucKhoe: IBlcGiayKhamSucKhoe) {
    this.editForm.patchValue({
      id: blcGiayKhamSucKhoe.id,
      uuid: blcGiayKhamSucKhoe.uuid,
      soGiayKSK: blcGiayKhamSucKhoe.soGiayKSK,
      hoTen: blcGiayKhamSucKhoe.hoTen,
      gioiTinh: blcGiayKhamSucKhoe.gioiTinh,
      tuoi: blcGiayKhamSucKhoe.tuoi,
      soCMND: blcGiayKhamSucKhoe.soCMND,
      ngayCap: blcGiayKhamSucKhoe.ngayCap,
      noiCap: blcGiayKhamSucKhoe.noiCap,
      noiO: blcGiayKhamSucKhoe.noiO,
      idBenhVien: blcGiayKhamSucKhoe.idBenhVien,
      tenBenhVien: blcGiayKhamSucKhoe.tenBenhVien,
      ngayKham: blcGiayKhamSucKhoe.ngayKham,
      hangBangLai: blcGiayKhamSucKhoe.hangBangLai,
      ketLuan: blcGiayKhamSucKhoe.ketLuan,
      bacSyKetLuan: blcGiayKhamSucKhoe.bacSyKetLuan,
      pdfGiayKSK: blcGiayKhamSucKhoe.pdfGiayKSK,
      status: blcGiayKhamSucKhoe.status
    });
  }

  previousState() {
    window.history.back();
  }

  save() {
    this.isSaving = true;
    const blcGiayKhamSucKhoe = this.createFromForm();
    if (blcGiayKhamSucKhoe.id !== undefined) {
      this.subscribeToSaveResponse(this.blcGiayKhamSucKhoeService.update(blcGiayKhamSucKhoe));
    } else {
      this.subscribeToSaveResponse(this.blcGiayKhamSucKhoeService.create(blcGiayKhamSucKhoe));
    }
  }

  private createFromForm(): IBlcGiayKhamSucKhoe {
    return {
      ...new BlcGiayKhamSucKhoe(),
      id: this.editForm.get(['id']).value,
      uuid: this.editForm.get(['uuid']).value,
      soGiayKSK: this.editForm.get(['soGiayKSK']).value,
      hoTen: this.editForm.get(['hoTen']).value,
      gioiTinh: this.editForm.get(['gioiTinh']).value,
      tuoi: this.editForm.get(['tuoi']).value,
      soCMND: this.editForm.get(['soCMND']).value,
      ngayCap: this.editForm.get(['ngayCap']).value,
      noiCap: this.editForm.get(['noiCap']).value,
      noiO: this.editForm.get(['noiO']).value,
      idBenhVien: this.editForm.get(['idBenhVien']).value,
      tenBenhVien: this.editForm.get(['tenBenhVien']).value,
      ngayKham: this.editForm.get(['ngayKham']).value,
      hangBangLai: this.editForm.get(['hangBangLai']).value,
      ketLuan: this.editForm.get(['ketLuan']).value,
      bacSyKetLuan: this.editForm.get(['bacSyKetLuan']).value,
      pdfGiayKSK: this.editForm.get(['pdfGiayKSK']).value,
      status: this.editForm.get(['status']).value
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IBlcGiayKhamSucKhoe>>) {
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

  trackDonViHanhChinhById(index: number, item: IDonViHanhChinh) {
    return item.id;
  }

  trackBenhVienById(index: number, item: IBenhVien) {
    return item.id;
  }
}
