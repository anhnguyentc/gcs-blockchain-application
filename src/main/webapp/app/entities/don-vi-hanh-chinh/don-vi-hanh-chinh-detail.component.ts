import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IDonViHanhChinh } from 'app/shared/model/don-vi-hanh-chinh.model';

@Component({
  selector: 'jhi-don-vi-hanh-chinh-detail',
  templateUrl: './don-vi-hanh-chinh-detail.component.html'
})
export class DonViHanhChinhDetailComponent implements OnInit {
  donViHanhChinh: IDonViHanhChinh;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ donViHanhChinh }) => {
      this.donViHanhChinh = donViHanhChinh;
    });
  }

  previousState() {
    window.history.back();
  }
}
