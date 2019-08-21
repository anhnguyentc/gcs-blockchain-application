import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IDonViHanhChinh } from 'app/shared/model/don-vi-hanh-chinh.model';
import { DonViHanhChinhService } from './don-vi-hanh-chinh.service';

@Component({
  selector: 'jhi-don-vi-hanh-chinh-delete-dialog',
  templateUrl: './don-vi-hanh-chinh-delete-dialog.component.html'
})
export class DonViHanhChinhDeleteDialogComponent {
  donViHanhChinh: IDonViHanhChinh;

  constructor(
    protected donViHanhChinhService: DonViHanhChinhService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  clear() {
    this.activeModal.dismiss('cancel');
  }

  confirmDelete(id: number) {
    this.donViHanhChinhService.delete(id).subscribe(response => {
      this.eventManager.broadcast({
        name: 'donViHanhChinhListModification',
        content: 'Deleted an donViHanhChinh'
      });
      this.activeModal.dismiss(true);
    });
  }
}

@Component({
  selector: 'jhi-don-vi-hanh-chinh-delete-popup',
  template: ''
})
export class DonViHanhChinhDeletePopupComponent implements OnInit, OnDestroy {
  protected ngbModalRef: NgbModalRef;

  constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ donViHanhChinh }) => {
      setTimeout(() => {
        this.ngbModalRef = this.modalService.open(DonViHanhChinhDeleteDialogComponent as Component, { size: 'lg', backdrop: 'static' });
        this.ngbModalRef.componentInstance.donViHanhChinh = donViHanhChinh;
        this.ngbModalRef.result.then(
          result => {
            this.router.navigate(['/don-vi-hanh-chinh', { outlets: { popup: null } }]);
            this.ngbModalRef = null;
          },
          reason => {
            this.router.navigate(['/don-vi-hanh-chinh', { outlets: { popup: null } }]);
            this.ngbModalRef = null;
          }
        );
      }, 0);
    });
  }

  ngOnDestroy() {
    this.ngbModalRef = null;
  }
}
