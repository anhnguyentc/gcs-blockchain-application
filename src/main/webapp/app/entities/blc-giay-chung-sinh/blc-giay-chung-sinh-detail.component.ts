import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IBlcGiayChungSinh } from 'app/shared/model/blc-giay-chung-sinh.model';

@Component({
  selector: 'jhi-blc-giay-chung-sinh-detail',
  templateUrl: './blc-giay-chung-sinh-detail.component.html'
})
export class BlcGiayChungSinhDetailComponent implements OnInit {
  blcGiayChungSinh: IBlcGiayChungSinh;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ blcGiayChungSinh }) => {
      this.blcGiayChungSinh = blcGiayChungSinh;
    });
  }

  previousState() {
    window.history.back();
  }
}
