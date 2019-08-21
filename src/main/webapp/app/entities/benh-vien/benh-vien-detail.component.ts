import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IBenhVien } from 'app/shared/model/benh-vien.model';

@Component({
  selector: 'jhi-benh-vien-detail',
  templateUrl: './benh-vien-detail.component.html'
})
export class BenhVienDetailComponent implements OnInit {
  benhVien: IBenhVien;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ benhVien }) => {
      this.benhVien = benhVien;
    });
  }

  previousState() {
    window.history.back();
  }
}
