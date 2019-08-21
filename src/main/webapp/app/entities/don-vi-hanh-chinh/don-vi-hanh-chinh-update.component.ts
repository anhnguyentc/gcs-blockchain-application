import { Component, OnInit } from '@angular/core';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { JhiAlertService } from 'ng-jhipster';
import { IDonViHanhChinh, DonViHanhChinh } from 'app/shared/model/don-vi-hanh-chinh.model';
import { DonViHanhChinhService } from './don-vi-hanh-chinh.service';
import { IBlcGiayChungSinh } from 'app/shared/model/blc-giay-chung-sinh.model';
import { BlcGiayChungSinhService } from 'app/entities/blc-giay-chung-sinh';
import { IBlcGiayKhamSucKhoe } from 'app/shared/model/blc-giay-kham-suc-khoe.model';
import { BlcGiayKhamSucKhoeService } from 'app/entities/blc-giay-kham-suc-khoe';

@Component({
  selector: 'jhi-don-vi-hanh-chinh-update',
  templateUrl: './don-vi-hanh-chinh-update.component.html'
})
export class DonViHanhChinhUpdateComponent implements OnInit {
  isSaving: boolean;

  blcgiaychungsinhs: IBlcGiayChungSinh[];

  blcgiaykhamsuckhoes: IBlcGiayKhamSucKhoe[];

  editForm = this.fb.group({
    id: [],
    maDonViHanhChinh: [null, [Validators.required]],
    tenDonViHanhChinh: [],
    ghiChu: [],
    status: [],
    blcGiayChungSinh: [],
    blcGiayKhamSucKhoe: []
  });

  constructor(
    protected jhiAlertService: JhiAlertService,
    protected donViHanhChinhService: DonViHanhChinhService,
    protected blcGiayChungSinhService: BlcGiayChungSinhService,
    protected blcGiayKhamSucKhoeService: BlcGiayKhamSucKhoeService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit() {
    this.isSaving = false;
    this.activatedRoute.data.subscribe(({ donViHanhChinh }) => {
      this.updateForm(donViHanhChinh);
    });
    this.blcGiayChungSinhService
      .query({ 'donViHanhChinhId.specified': 'false' })
      .pipe(
        filter((mayBeOk: HttpResponse<IBlcGiayChungSinh[]>) => mayBeOk.ok),
        map((response: HttpResponse<IBlcGiayChungSinh[]>) => response.body)
      )
      .subscribe(
        (res: IBlcGiayChungSinh[]) => {
          if (!this.editForm.get('blcGiayChungSinh').value || !this.editForm.get('blcGiayChungSinh').value.id) {
            this.blcgiaychungsinhs = res;
          } else {
            this.blcGiayChungSinhService
              .find(this.editForm.get('blcGiayChungSinh').value.id)
              .pipe(
                filter((subResMayBeOk: HttpResponse<IBlcGiayChungSinh>) => subResMayBeOk.ok),
                map((subResponse: HttpResponse<IBlcGiayChungSinh>) => subResponse.body)
              )
              .subscribe(
                (subRes: IBlcGiayChungSinh) => (this.blcgiaychungsinhs = [subRes].concat(res)),
                (subRes: HttpErrorResponse) => this.onError(subRes.message)
              );
          }
        },
        (res: HttpErrorResponse) => this.onError(res.message)
      );
    this.blcGiayKhamSucKhoeService
      .query({ 'donViHanhChinhId.specified': 'false' })
      .pipe(
        filter((mayBeOk: HttpResponse<IBlcGiayKhamSucKhoe[]>) => mayBeOk.ok),
        map((response: HttpResponse<IBlcGiayKhamSucKhoe[]>) => response.body)
      )
      .subscribe(
        (res: IBlcGiayKhamSucKhoe[]) => {
          if (!this.editForm.get('blcGiayKhamSucKhoe').value || !this.editForm.get('blcGiayKhamSucKhoe').value.id) {
            this.blcgiaykhamsuckhoes = res;
          } else {
            this.blcGiayKhamSucKhoeService
              .find(this.editForm.get('blcGiayKhamSucKhoe').value.id)
              .pipe(
                filter((subResMayBeOk: HttpResponse<IBlcGiayKhamSucKhoe>) => subResMayBeOk.ok),
                map((subResponse: HttpResponse<IBlcGiayKhamSucKhoe>) => subResponse.body)
              )
              .subscribe(
                (subRes: IBlcGiayKhamSucKhoe) => (this.blcgiaykhamsuckhoes = [subRes].concat(res)),
                (subRes: HttpErrorResponse) => this.onError(subRes.message)
              );
          }
        },
        (res: HttpErrorResponse) => this.onError(res.message)
      );
  }

  updateForm(donViHanhChinh: IDonViHanhChinh) {
    this.editForm.patchValue({
      id: donViHanhChinh.id,
      maDonViHanhChinh: donViHanhChinh.maDonViHanhChinh,
      tenDonViHanhChinh: donViHanhChinh.tenDonViHanhChinh,
      ghiChu: donViHanhChinh.ghiChu,
      status: donViHanhChinh.status,
      blcGiayChungSinh: donViHanhChinh.blcGiayChungSinh,
      blcGiayKhamSucKhoe: donViHanhChinh.blcGiayKhamSucKhoe
    });
  }

  previousState() {
    window.history.back();
  }

  save() {
    this.isSaving = true;
    const donViHanhChinh = this.createFromForm();
    if (donViHanhChinh.id !== undefined) {
      this.subscribeToSaveResponse(this.donViHanhChinhService.update(donViHanhChinh));
    } else {
      this.subscribeToSaveResponse(this.donViHanhChinhService.create(donViHanhChinh));
    }
  }

  private createFromForm(): IDonViHanhChinh {
    return {
      ...new DonViHanhChinh(),
      id: this.editForm.get(['id']).value,
      maDonViHanhChinh: this.editForm.get(['maDonViHanhChinh']).value,
      tenDonViHanhChinh: this.editForm.get(['tenDonViHanhChinh']).value,
      ghiChu: this.editForm.get(['ghiChu']).value,
      status: this.editForm.get(['status']).value,
      blcGiayChungSinh: this.editForm.get(['blcGiayChungSinh']).value,
      blcGiayKhamSucKhoe: this.editForm.get(['blcGiayKhamSucKhoe']).value
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IDonViHanhChinh>>) {
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

  trackBlcGiayChungSinhById(index: number, item: IBlcGiayChungSinh) {
    return item.id;
  }

  trackBlcGiayKhamSucKhoeById(index: number, item: IBlcGiayKhamSucKhoe) {
    return item.id;
  }
}
