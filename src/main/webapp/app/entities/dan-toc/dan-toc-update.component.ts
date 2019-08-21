import { Component, OnInit } from '@angular/core';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { JhiAlertService } from 'ng-jhipster';
import { IDanToc, DanToc } from 'app/shared/model/dan-toc.model';
import { DanTocService } from './dan-toc.service';
import { IBlcGiayChungSinh } from 'app/shared/model/blc-giay-chung-sinh.model';
import { BlcGiayChungSinhService } from 'app/entities/blc-giay-chung-sinh';

@Component({
  selector: 'jhi-dan-toc-update',
  templateUrl: './dan-toc-update.component.html'
})
export class DanTocUpdateComponent implements OnInit {
  isSaving: boolean;

  blcgiaychungsinhs: IBlcGiayChungSinh[];

  editForm = this.fb.group({
    id: [],
    maDanToc: [],
    tenDanToc: [],
    ghiChu: [],
    status: [],
    blcGiayChungSinh: []
  });

  constructor(
    protected jhiAlertService: JhiAlertService,
    protected danTocService: DanTocService,
    protected blcGiayChungSinhService: BlcGiayChungSinhService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit() {
    this.isSaving = false;
    this.activatedRoute.data.subscribe(({ danToc }) => {
      this.updateForm(danToc);
    });
    this.blcGiayChungSinhService
      .query({ 'danTocId.specified': 'false' })
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
  }

  updateForm(danToc: IDanToc) {
    this.editForm.patchValue({
      id: danToc.id,
      maDanToc: danToc.maDanToc,
      tenDanToc: danToc.tenDanToc,
      ghiChu: danToc.ghiChu,
      status: danToc.status,
      blcGiayChungSinh: danToc.blcGiayChungSinh
    });
  }

  previousState() {
    window.history.back();
  }

  save() {
    this.isSaving = true;
    const danToc = this.createFromForm();
    if (danToc.id !== undefined) {
      this.subscribeToSaveResponse(this.danTocService.update(danToc));
    } else {
      this.subscribeToSaveResponse(this.danTocService.create(danToc));
    }
  }

  private createFromForm(): IDanToc {
    return {
      ...new DanToc(),
      id: this.editForm.get(['id']).value,
      maDanToc: this.editForm.get(['maDanToc']).value,
      tenDanToc: this.editForm.get(['tenDanToc']).value,
      ghiChu: this.editForm.get(['ghiChu']).value,
      status: this.editForm.get(['status']).value,
      blcGiayChungSinh: this.editForm.get(['blcGiayChungSinh']).value
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IDanToc>>) {
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
