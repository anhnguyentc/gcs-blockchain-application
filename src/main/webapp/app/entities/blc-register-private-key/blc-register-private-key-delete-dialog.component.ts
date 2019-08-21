import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IBlcRegisterPrivateKey } from 'app/shared/model/blc-register-private-key.model';
import { BlcRegisterPrivateKeyService } from './blc-register-private-key.service';

@Component({
  selector: 'jhi-blc-register-private-key-delete-dialog',
  templateUrl: './blc-register-private-key-delete-dialog.component.html'
})
export class BlcRegisterPrivateKeyDeleteDialogComponent {
  blcRegisterPrivateKey: IBlcRegisterPrivateKey;

  constructor(
    protected blcRegisterPrivateKeyService: BlcRegisterPrivateKeyService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  clear() {
    this.activeModal.dismiss('cancel');
  }

  confirmDelete(id: number) {
    this.blcRegisterPrivateKeyService.delete(id).subscribe(response => {
      this.eventManager.broadcast({
        name: 'blcRegisterPrivateKeyListModification',
        content: 'Deleted an blcRegisterPrivateKey'
      });
      this.activeModal.dismiss(true);
    });
  }
}

@Component({
  selector: 'jhi-blc-register-private-key-delete-popup',
  template: ''
})
export class BlcRegisterPrivateKeyDeletePopupComponent implements OnInit, OnDestroy {
  protected ngbModalRef: NgbModalRef;

  constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ blcRegisterPrivateKey }) => {
      setTimeout(() => {
        this.ngbModalRef = this.modalService.open(BlcRegisterPrivateKeyDeleteDialogComponent as Component, {
          size: 'lg',
          backdrop: 'static'
        });
        this.ngbModalRef.componentInstance.blcRegisterPrivateKey = blcRegisterPrivateKey;
        this.ngbModalRef.result.then(
          result => {
            this.router.navigate(['/blc-register-private-key', { outlets: { popup: null } }]);
            this.ngbModalRef = null;
          },
          reason => {
            this.router.navigate(['/blc-register-private-key', { outlets: { popup: null } }]);
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
