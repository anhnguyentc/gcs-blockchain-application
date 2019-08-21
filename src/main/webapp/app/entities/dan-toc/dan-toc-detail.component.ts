import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IDanToc } from 'app/shared/model/dan-toc.model';

@Component({
  selector: 'jhi-dan-toc-detail',
  templateUrl: './dan-toc-detail.component.html'
})
export class DanTocDetailComponent implements OnInit {
  danToc: IDanToc;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ danToc }) => {
      this.danToc = danToc;
    });
  }

  previousState() {
    window.history.back();
  }
}
