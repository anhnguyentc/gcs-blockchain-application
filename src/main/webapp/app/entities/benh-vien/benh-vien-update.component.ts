import { Component, OnInit } from '@angular/core';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { JhiAlertService } from 'ng-jhipster';
import { IBenhVien, BenhVien } from 'app/shared/model/benh-vien.model';
import { BenhVienService } from './benh-vien.service';
import { IBlcGiayChungSinh } from 'app/shared/model/blc-giay-chung-sinh.model';
import { BlcGiayChungSinhService } from 'app/entities/blc-giay-chung-sinh';
import { IBlcGiayKhamSucKhoe } from 'app/shared/model/blc-giay-kham-suc-khoe.model';
import { BlcGiayKhamSucKhoeService } from 'app/entities/blc-giay-kham-suc-khoe';

@Component({
  selector: 'jhi-benh-vien-update',
  templateUrl: './benh-vien-update.component.html'
})
export class BenhVienUpdateComponent implements OnInit {
  isSaving: boolean;

  blcgiaychungsinhs: IBlcGiayChungSinh[];

  blcgiaykhamsuckhoes: IBlcGiayKhamSucKhoe[];

  editForm = this.fb.group({
    id: [],
    maBenhVien: [null, [Validators.required]],
    tenBenhVien: [],
    ghiChu: [],
    status: [],
    blcGiayChungSinh: [],
    blcGiayKhamSucKhoe: []
  });

  constructor(
    protected jhiAlertService: JhiAlertService,
    protected benhVienService: BenhVienService,
    protected blcGiayChungSinhService: BlcGiayChungSinhService,
    protected blcGiayKhamSucKhoeService: BlcGiayKhamSucKhoeService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit() {
    this.isSaving = false;
    this.activatedRoute.data.subscribe(({ benhVien }) => {
      this.updateForm(benhVien);
    });
    this.blcGiayChungSinhService
      .query({ 'benhVienId.specified': 'false' })
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
      .query({ 'benhVienId.specified': 'false' })
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

  updateForm(benhVien: IBenhVien) {
    this.editForm.patchValue({
      id: benhVien.id,
      maBenhVien: benhVien.maBenhVien,
      tenBenhVien: benhVien.tenBenhVien,
      ghiChu: benhVien.ghiChu,
      status: benhVien.status,
      blcGiayChungSinh: benhVien.blcGiayChungSinh,
      blcGiayKhamSucKhoe: benhVien.blcGiayKhamSucKhoe
    });
  }

  previousState() {
    window.history.back();
  }

  save() {
    this.isSaving = true;
    const benhVien = this.createFromForm();
    if (benhVien.id !== undefined) {
      this.subscribeToSaveResponse(this.benhVienService.update(benhVien));
    } else {
      this.subscribeToSaveResponse(this.benhVienService.create(benhVien));
    }
  }

  private createFromForm(): IBenhVien {
    return {
      ...new BenhVien(),
      id: this.editForm.get(['id']).value,
      maBenhVien: this.editForm.get(['maBenhVien']).value,
      tenBenhVien: this.editForm.get(['tenBenhVien']).value,
      ghiChu: this.editForm.get(['ghiChu']).value,
      status: this.editForm.get(['status']).value,
      blcGiayChungSinh: this.editForm.get(['blcGiayChungSinh']).value,
      blcGiayKhamSucKhoe: this.editForm.get(['blcGiayKhamSucKhoe']).value
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IBenhVien>>) {
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
