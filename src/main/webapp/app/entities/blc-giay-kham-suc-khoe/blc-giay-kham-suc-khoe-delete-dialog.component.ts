import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IBlcGiayKhamSucKhoe } from 'app/shared/model/blc-giay-kham-suc-khoe.model';
import { BlcGiayKhamSucKhoeService } from './blc-giay-kham-suc-khoe.service';

@Component({
  selector: 'jhi-blc-giay-kham-suc-khoe-delete-dialog',
  templateUrl: './blc-giay-kham-suc-khoe-delete-dialog.component.html'
})
export class BlcGiayKhamSucKhoeDeleteDialogComponent {
  blcGiayKhamSucKhoe: IBlcGiayKhamSucKhoe;

  constructor(
    protected blcGiayKhamSucKhoeService: BlcGiayKhamSucKhoeService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  clear() {
    this.activeModal.dismiss('cancel');
  }

  confirmDelete(id: number) {
    this.blcGiayKhamSucKhoeService.delete(id).subscribe(response => {
      this.eventManager.broadcast({
        name: 'blcGiayKhamSucKhoeListModification',
        content: 'Deleted an blcGiayKhamSucKhoe'
      });
      this.activeModal.dismiss(true);
    });
  }
}

@Component({
  selector: 'jhi-blc-giay-kham-suc-khoe-delete-popup',
  template: ''
})
export class BlcGiayKhamSucKhoeDeletePopupComponent implements OnInit, OnDestroy {
  protected ngbModalRef: NgbModalRef;

  constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ blcGiayKhamSucKhoe }) => {
      setTimeout(() => {
        this.ngbModalRef = this.modalService.open(BlcGiayKhamSucKhoeDeleteDialogComponent as Component, { size: 'lg', backdrop: 'static' });
        this.ngbModalRef.componentInstance.blcGiayKhamSucKhoe = blcGiayKhamSucKhoe;
        this.ngbModalRef.result.then(
          result => {
            this.router.navigate(['/blc-giay-kham-suc-khoe', { outlets: { popup: null } }]);
            this.ngbModalRef = null;
          },
          reason => {
            this.router.navigate(['/blc-giay-kham-suc-khoe', { outlets: { popup: null } }]);
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
