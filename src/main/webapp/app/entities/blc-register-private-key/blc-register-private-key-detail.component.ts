import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IBlcRegisterPrivateKey } from 'app/shared/model/blc-register-private-key.model';

@Component({
  selector: 'jhi-blc-register-private-key-detail',
  templateUrl: './blc-register-private-key-detail.component.html'
})
export class BlcRegisterPrivateKeyDetailComponent implements OnInit {
  blcRegisterPrivateKey: IBlcRegisterPrivateKey;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ blcRegisterPrivateKey }) => {
      this.blcRegisterPrivateKey = blcRegisterPrivateKey;
    });
  }

  previousState() {
    window.history.back();
  }
}
