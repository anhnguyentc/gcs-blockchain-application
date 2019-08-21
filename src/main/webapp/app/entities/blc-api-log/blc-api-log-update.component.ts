import { Component, OnInit } from '@angular/core';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import * as moment from 'moment';
import { JhiAlertService } from 'ng-jhipster';
import { IBlcAPILog, BlcAPILog } from 'app/shared/model/blc-api-log.model';
import { BlcAPILogService } from './blc-api-log.service';
import { IBlcGiayChungSinh } from 'app/shared/model/blc-giay-chung-sinh.model';
import { BlcGiayChungSinhService } from 'app/entities/blc-giay-chung-sinh';

@Component({
  selector: 'jhi-blc-api-log-update',
  templateUrl: './blc-api-log-update.component.html'
})
export class BlcAPILogUpdateComponent implements OnInit {
  isSaving: boolean;

  blcgiaychungsinhs: IBlcGiayChungSinh[];
  loadedTimeDp: any;

  editForm = this.fb.group({
    id: [],
    uuid: [null, [Validators.required]],
    status: [],
    messageStatus: [],
    methodName: [],
    soGCS: [],
    soSOGCS: [],
    requestContent: [],
    errorMessage: [],
    loadedTime: [],
    ipAddress: [],
    idBenhvien: [],
    tenBenhvien: [],
    processTime: [],
    responseTime: []
  });

  constructor(
    protected jhiAlertService: JhiAlertService,
    protected blcAPILogService: BlcAPILogService,
    protected blcGiayChungSinhService: BlcGiayChungSinhService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit() {
    this.isSaving = false;
    this.activatedRoute.data.subscribe(({ blcAPILog }) => {
      this.updateForm(blcAPILog);
    });
    this.blcGiayChungSinhService
      .query()
      .pipe(
        filter((mayBeOk: HttpResponse<IBlcGiayChungSinh[]>) => mayBeOk.ok),
        map((response: HttpResponse<IBlcGiayChungSinh[]>) => response.body)
      )
      .subscribe((res: IBlcGiayChungSinh[]) => (this.blcgiaychungsinhs = res), (res: HttpErrorResponse) => this.onError(res.message));
  }

  updateForm(blcAPILog: IBlcAPILog) {
    this.editForm.patchValue({
      id: blcAPILog.id,
      uuid: blcAPILog.uuid,
      status: blcAPILog.status,
      messageStatus: blcAPILog.messageStatus,
      methodName: blcAPILog.methodName,
      soGCS: blcAPILog.soGCS,
      soSOGCS: blcAPILog.soSOGCS,
      requestContent: blcAPILog.requestContent,
      errorMessage: blcAPILog.errorMessage,
      loadedTime: blcAPILog.loadedTime,
      ipAddress: blcAPILog.ipAddress,
      idBenhvien: blcAPILog.idBenhvien,
      tenBenhvien: blcAPILog.tenBenhvien,
      processTime: blcAPILog.processTime,
      responseTime: blcAPILog.responseTime
    });
  }

  previousState() {
    window.history.back();
  }

  save() {
    this.isSaving = true;
    const blcAPILog = this.createFromForm();
    if (blcAPILog.id !== undefined) {
      this.subscribeToSaveResponse(this.blcAPILogService.update(blcAPILog));
    } else {
      this.subscribeToSaveResponse(this.blcAPILogService.create(blcAPILog));
    }
  }

  private createFromForm(): IBlcAPILog {
    return {
      ...new BlcAPILog(),
      id: this.editForm.get(['id']).value,
      uuid: this.editForm.get(['uuid']).value,
      status: this.editForm.get(['status']).value,
      messageStatus: this.editForm.get(['messageStatus']).value,
      methodName: this.editForm.get(['methodName']).value,
      soGCS: this.editForm.get(['soGCS']).value,
      soSOGCS: this.editForm.get(['soSOGCS']).value,
      requestContent: this.editForm.get(['requestContent']).value,
      errorMessage: this.editForm.get(['errorMessage']).value,
      loadedTime: this.editForm.get(['loadedTime']).value,
      ipAddress: this.editForm.get(['ipAddress']).value,
      idBenhvien: this.editForm.get(['idBenhvien']).value,
      tenBenhvien: this.editForm.get(['tenBenhvien']).value,
      processTime: this.editForm.get(['processTime']).value,
      responseTime: this.editForm.get(['responseTime']).value
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IBlcAPILog>>) {
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
