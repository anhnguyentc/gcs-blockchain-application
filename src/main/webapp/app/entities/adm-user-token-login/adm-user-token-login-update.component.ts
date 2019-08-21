import { Component, OnInit } from '@angular/core';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { IAdmUserTokenLogin, AdmUserTokenLogin } from 'app/shared/model/adm-user-token-login.model';
import { AdmUserTokenLoginService } from './adm-user-token-login.service';

@Component({
  selector: 'jhi-adm-user-token-login-update',
  templateUrl: './adm-user-token-login-update.component.html'
})
export class AdmUserTokenLoginUpdateComponent implements OnInit {
  isSaving: boolean;
  createdDateDp: any;
  expirationDateDp: any;

  editForm = this.fb.group({
    id: [],
    userName: [null, [Validators.maxLength(255)]],
    createdDate: [],
    expirationDate: [],
    loginIP: [],
    active: [],
    heThong: [],
    tokenType: [],
    status: []
  });

  constructor(
    protected admUserTokenLoginService: AdmUserTokenLoginService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit() {
    this.isSaving = false;
    this.activatedRoute.data.subscribe(({ admUserTokenLogin }) => {
      this.updateForm(admUserTokenLogin);
    });
  }

  updateForm(admUserTokenLogin: IAdmUserTokenLogin) {
    this.editForm.patchValue({
      id: admUserTokenLogin.id,
      userName: admUserTokenLogin.userName,
      createdDate: admUserTokenLogin.createdDate,
      expirationDate: admUserTokenLogin.expirationDate,
      loginIP: admUserTokenLogin.loginIP,
      active: admUserTokenLogin.active,
      heThong: admUserTokenLogin.heThong,
      tokenType: admUserTokenLogin.tokenType,
      status: admUserTokenLogin.status
    });
  }

  previousState() {
    window.history.back();
  }

  save() {
    this.isSaving = true;
    const admUserTokenLogin = this.createFromForm();
    if (admUserTokenLogin.id !== undefined) {
      this.subscribeToSaveResponse(this.admUserTokenLoginService.update(admUserTokenLogin));
    } else {
      this.subscribeToSaveResponse(this.admUserTokenLoginService.create(admUserTokenLogin));
    }
  }

  private createFromForm(): IAdmUserTokenLogin {
    return {
      ...new AdmUserTokenLogin(),
      id: this.editForm.get(['id']).value,
      userName: this.editForm.get(['userName']).value,
      createdDate: this.editForm.get(['createdDate']).value,
      expirationDate: this.editForm.get(['expirationDate']).value,
      loginIP: this.editForm.get(['loginIP']).value,
      active: this.editForm.get(['active']).value,
      heThong: this.editForm.get(['heThong']).value,
      tokenType: this.editForm.get(['tokenType']).value,
      status: this.editForm.get(['status']).value
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IAdmUserTokenLogin>>) {
    result.subscribe(() => this.onSaveSuccess(), () => this.onSaveError());
  }

  protected onSaveSuccess() {
    this.isSaving = false;
    this.previousState();
  }

  protected onSaveError() {
    this.isSaving = false;
  }
}
