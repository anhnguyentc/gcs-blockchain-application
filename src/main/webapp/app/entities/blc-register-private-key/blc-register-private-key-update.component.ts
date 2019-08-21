import { Component, OnInit } from '@angular/core';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { JhiAlertService } from 'ng-jhipster';
import { IBlcRegisterPrivateKey, BlcRegisterPrivateKey } from 'app/shared/model/blc-register-private-key.model';
import { BlcRegisterPrivateKeyService } from './blc-register-private-key.service';
import { IBlcGiayChungSinh } from 'app/shared/model/blc-giay-chung-sinh.model';
import { BlcGiayChungSinhService } from 'app/entities/blc-giay-chung-sinh';

@Component({
  selector: 'jhi-blc-register-private-key-update',
  templateUrl: './blc-register-private-key-update.component.html'
})
export class BlcRegisterPrivateKeyUpdateComponent implements OnInit {
  isSaving: boolean;

  blcgiaychungsinhs: IBlcGiayChungSinh[];

  editForm = this.fb.group({
    id: [],
    idBenhVien: [null, [Validators.required]],
    tenBenhVien: [null, [Validators.required]],
    uuidAcccount: [],
    uuidGCSDB: [],
    idUserCreate: [],
    codeSoft: [],
    publicKey: [],
    addressKey: [],
    createdDate: [],
    modifiedDate: [],
    status: [],
    blcGiayChungSinh: []
  });

  constructor(
    protected jhiAlertService: JhiAlertService,
    protected blcRegisterPrivateKeyService: BlcRegisterPrivateKeyService,
    protected blcGiayChungSinhService: BlcGiayChungSinhService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit() {
    this.isSaving = false;
    this.activatedRoute.data.subscribe(({ blcRegisterPrivateKey }) => {
      this.updateForm(blcRegisterPrivateKey);
    });
    this.blcGiayChungSinhService
      .query()
      .pipe(
        filter((mayBeOk: HttpResponse<IBlcGiayChungSinh[]>) => mayBeOk.ok),
        map((response: HttpResponse<IBlcGiayChungSinh[]>) => response.body)
      )
      .subscribe((res: IBlcGiayChungSinh[]) => (this.blcgiaychungsinhs = res), (res: HttpErrorResponse) => this.onError(res.message));
  }

  updateForm(blcRegisterPrivateKey: IBlcRegisterPrivateKey) {
    this.editForm.patchValue({
      id: blcRegisterPrivateKey.id,
      idBenhVien: blcRegisterPrivateKey.idBenhVien,
      tenBenhVien: blcRegisterPrivateKey.tenBenhVien,
      uuidAcccount: blcRegisterPrivateKey.uuidAcccount,
      uuidGCSDB: blcRegisterPrivateKey.uuidGCSDB,
      idUserCreate: blcRegisterPrivateKey.idUserCreate,
      codeSoft: blcRegisterPrivateKey.codeSoft,
      publicKey: blcRegisterPrivateKey.publicKey,
      addressKey: blcRegisterPrivateKey.addressKey,
      createdDate: blcRegisterPrivateKey.createdDate,
      modifiedDate: blcRegisterPrivateKey.modifiedDate,
      status: blcRegisterPrivateKey.status,
      blcGiayChungSinh: blcRegisterPrivateKey.blcGiayChungSinh
    });
  }

  previousState() {
    window.history.back();
  }

  save() {
    this.isSaving = true;
    const blcRegisterPrivateKey = this.createFromForm();
    if (blcRegisterPrivateKey.id !== undefined) {
      this.subscribeToSaveResponse(this.blcRegisterPrivateKeyService.update(blcRegisterPrivateKey));
    } else {
      this.subscribeToSaveResponse(this.blcRegisterPrivateKeyService.create(blcRegisterPrivateKey));
    }
  }

  private createFromForm(): IBlcRegisterPrivateKey {
    return {
      ...new BlcRegisterPrivateKey(),
      id: this.editForm.get(['id']).value,
      idBenhVien: this.editForm.get(['idBenhVien']).value,
      tenBenhVien: this.editForm.get(['tenBenhVien']).value,
      uuidAcccount: this.editForm.get(['uuidAcccount']).value,
      uuidGCSDB: this.editForm.get(['uuidGCSDB']).value,
      idUserCreate: this.editForm.get(['idUserCreate']).value,
      codeSoft: this.editForm.get(['codeSoft']).value,
      publicKey: this.editForm.get(['publicKey']).value,
      addressKey: this.editForm.get(['addressKey']).value,
      createdDate: this.editForm.get(['createdDate']).value,
      modifiedDate: this.editForm.get(['modifiedDate']).value,
      status: this.editForm.get(['status']).value,
      blcGiayChungSinh: this.editForm.get(['blcGiayChungSinh']).value
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IBlcRegisterPrivateKey>>) {
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
}
