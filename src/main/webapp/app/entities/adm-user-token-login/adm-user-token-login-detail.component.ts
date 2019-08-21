import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IAdmUserTokenLogin } from 'app/shared/model/adm-user-token-login.model';

@Component({
  selector: 'jhi-adm-user-token-login-detail',
  templateUrl: './adm-user-token-login-detail.component.html'
})
export class AdmUserTokenLoginDetailComponent implements OnInit {
  admUserTokenLogin: IAdmUserTokenLogin;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ admUserTokenLogin }) => {
      this.admUserTokenLogin = admUserTokenLogin;
    });
  }

  previousState() {
    window.history.back();
  }
}
