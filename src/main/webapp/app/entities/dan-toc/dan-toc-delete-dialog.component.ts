import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IDanToc } from 'app/shared/model/dan-toc.model';
import { DanTocService } from './dan-toc.service';

@Component({
  selector: 'jhi-dan-toc-delete-dialog',
  templateUrl: './dan-toc-delete-dialog.component.html'
})
export class DanTocDeleteDialogComponent {
  danToc: IDanToc;

  constructor(protected danTocService: DanTocService, public activeModal: NgbActiveModal, protected eventManager: JhiEventManager) {}

  clear() {
    this.activeModal.dismiss('cancel');
  }

  confirmDelete(id: number) {
    this.danTocService.delete(id).subscribe(response => {
      this.eventManager.broadcast({
        name: 'danTocListModification',
        content: 'Deleted an danToc'
      });
      this.activeModal.dismiss(true);
    });
  }
}

@Component({
  selector: 'jhi-dan-toc-delete-popup',
  template: ''
})
export class DanTocDeletePopupComponent implements OnInit, OnDestroy {
  protected ngbModalRef: NgbModalRef;

  constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ danToc }) => {
      setTimeout(() => {
        this.ngbModalRef = this.modalService.open(DanTocDeleteDialogComponent as Component, { size: 'lg', backdrop: 'static' });
        this.ngbModalRef.componentInstance.danToc = danToc;
        this.ngbModalRef.result.then(
          result => {
            this.router.navigate(['/dan-toc', { outlets: { popup: null } }]);
            this.ngbModalRef = null;
          },
          reason => {
            this.router.navigate(['/dan-toc', { outlets: { popup: null } }]);
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
