import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IBlcGiayKhamSucKhoe } from 'app/shared/model/blc-giay-kham-suc-khoe.model';

@Component({
  selector: 'jhi-blc-giay-kham-suc-khoe-detail',
  templateUrl: './blc-giay-kham-suc-khoe-detail.component.html'
})
export class BlcGiayKhamSucKhoeDetailComponent implements OnInit {
  blcGiayKhamSucKhoe: IBlcGiayKhamSucKhoe;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ blcGiayKhamSucKhoe }) => {
      this.blcGiayKhamSucKhoe = blcGiayKhamSucKhoe;
    });
  }

  previousState() {
    window.history.back();
  }
}
