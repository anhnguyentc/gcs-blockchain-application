import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IBlcGiayChungSinh } from 'app/shared/model/blc-giay-chung-sinh.model';
import { BlcGiayChungSinhService } from './blc-giay-chung-sinh.service';

@Component({
  selector: 'jhi-blc-giay-chung-sinh-delete-dialog',
  templateUrl: './blc-giay-chung-sinh-delete-dialog.component.html'
})
export class BlcGiayChungSinhDeleteDialogComponent {
  blcGiayChungSinh: IBlcGiayChungSinh;

  constructor(
    protected blcGiayChungSinhService: BlcGiayChungSinhService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  clear() {
    this.activeModal.dismiss('cancel');
  }

  confirmDelete(id: number) {
    this.blcGiayChungSinhService.delete(id).subscribe(response => {
      this.eventManager.broadcast({
        name: 'blcGiayChungSinhListModification',
        content: 'Deleted an blcGiayChungSinh'
      });
      this.activeModal.dismiss(true);
    });
  }
}

@Component({
  selector: 'jhi-blc-giay-chung-sinh-delete-popup',
  template: ''
})
export class BlcGiayChungSinhDeletePopupComponent implements OnInit, OnDestroy {
  protected ngbModalRef: NgbModalRef;

  constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ blcGiayChungSinh }) => {
      setTimeout(() => {
        this.ngbModalRef = this.modalService.open(BlcGiayChungSinhDeleteDialogComponent as Component, { size: 'lg', backdrop: 'static' });
        this.ngbModalRef.componentInstance.blcGiayChungSinh = blcGiayChungSinh;
        this.ngbModalRef.result.then(
          result => {
            this.router.navigate(['/blc-giay-chung-sinh', { outlets: { popup: null } }]);
            this.ngbModalRef = null;
          },
          reason => {
            this.router.navigate(['/blc-giay-chung-sinh', { outlets: { popup: null } }]);
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
