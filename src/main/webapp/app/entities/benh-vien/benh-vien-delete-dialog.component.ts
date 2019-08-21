import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IBenhVien } from 'app/shared/model/benh-vien.model';
import { BenhVienService } from './benh-vien.service';

@Component({
  selector: 'jhi-benh-vien-delete-dialog',
  templateUrl: './benh-vien-delete-dialog.component.html'
})
export class BenhVienDeleteDialogComponent {
  benhVien: IBenhVien;

  constructor(protected benhVienService: BenhVienService, public activeModal: NgbActiveModal, protected eventManager: JhiEventManager) {}

  clear() {
    this.activeModal.dismiss('cancel');
  }

  confirmDelete(id: number) {
    this.benhVienService.delete(id).subscribe(response => {
      this.eventManager.broadcast({
        name: 'benhVienListModification',
        content: 'Deleted an benhVien'
      });
      this.activeModal.dismiss(true);
    });
  }
}

@Component({
  selector: 'jhi-benh-vien-delete-popup',
  template: ''
})
export class BenhVienDeletePopupComponent implements OnInit, OnDestroy {
  protected ngbModalRef: NgbModalRef;

  constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ benhVien }) => {
      setTimeout(() => {
        this.ngbModalRef = this.modalService.open(BenhVienDeleteDialogComponent as Component, { size: 'lg', backdrop: 'static' });
        this.ngbModalRef.componentInstance.benhVien = benhVien;
        this.ngbModalRef.result.then(
          result => {
            this.router.navigate(['/benh-vien', { outlets: { popup: null } }]);
            this.ngbModalRef = null;
          },
          reason => {
            this.router.navigate(['/benh-vien', { outlets: { popup: null } }]);
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
