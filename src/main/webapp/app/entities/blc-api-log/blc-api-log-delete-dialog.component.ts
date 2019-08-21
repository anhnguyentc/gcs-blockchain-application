import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IBlcAPILog } from 'app/shared/model/blc-api-log.model';
import { BlcAPILogService } from './blc-api-log.service';

@Component({
  selector: 'jhi-blc-api-log-delete-dialog',
  templateUrl: './blc-api-log-delete-dialog.component.html'
})
export class BlcAPILogDeleteDialogComponent {
  blcAPILog: IBlcAPILog;

  constructor(protected blcAPILogService: BlcAPILogService, public activeModal: NgbActiveModal, protected eventManager: JhiEventManager) {}

  clear() {
    this.activeModal.dismiss('cancel');
  }

  confirmDelete(id: number) {
    this.blcAPILogService.delete(id).subscribe(response => {
      this.eventManager.broadcast({
        name: 'blcAPILogListModification',
        content: 'Deleted an blcAPILog'
      });
      this.activeModal.dismiss(true);
    });
  }
}

@Component({
  selector: 'jhi-blc-api-log-delete-popup',
  template: ''
})
export class BlcAPILogDeletePopupComponent implements OnInit, OnDestroy {
  protected ngbModalRef: NgbModalRef;

  constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ blcAPILog }) => {
      setTimeout(() => {
        this.ngbModalRef = this.modalService.open(BlcAPILogDeleteDialogComponent as Component, { size: 'lg', backdrop: 'static' });
        this.ngbModalRef.componentInstance.blcAPILog = blcAPILog;
        this.ngbModalRef.result.then(
          result => {
            this.router.navigate(['/blc-api-log', { outlets: { popup: null } }]);
            this.ngbModalRef = null;
          },
          reason => {
            this.router.navigate(['/blc-api-log', { outlets: { popup: null } }]);
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
