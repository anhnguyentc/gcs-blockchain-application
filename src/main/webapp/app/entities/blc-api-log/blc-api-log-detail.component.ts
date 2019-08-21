import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IBlcAPILog } from 'app/shared/model/blc-api-log.model';

@Component({
  selector: 'jhi-blc-api-log-detail',
  templateUrl: './blc-api-log-detail.component.html'
})
export class BlcAPILogDetailComponent implements OnInit {
  blcAPILog: IBlcAPILog;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ blcAPILog }) => {
      this.blcAPILog = blcAPILog;
    });
  }

  previousState() {
    window.history.back();
  }
}
