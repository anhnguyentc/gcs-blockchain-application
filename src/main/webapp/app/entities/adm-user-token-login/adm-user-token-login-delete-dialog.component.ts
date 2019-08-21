import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IAdmUserTokenLogin } from 'app/shared/model/adm-user-token-login.model';
import { AdmUserTokenLoginService } from './adm-user-token-login.service';

@Component({
  selector: 'jhi-adm-user-token-login-delete-dialog',
  templateUrl: './adm-user-token-login-delete-dialog.component.html'
})
export class AdmUserTokenLoginDeleteDialogComponent {
  admUserTokenLogin: IAdmUserTokenLogin;

  constructor(
    protected admUserTokenLoginService: AdmUserTokenLoginService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  clear() {
    this.activeModal.dismiss('cancel');
  }

  confirmDelete(id: number) {
    this.admUserTokenLoginService.delete(id).subscribe(response => {
      this.eventManager.broadcast({
        name: 'admUserTokenLoginListModification',
        content: 'Deleted an admUserTokenLogin'
      });
      this.activeModal.dismiss(true);
    });
  }
}

@Component({
  selector: 'jhi-adm-user-token-login-delete-popup',
  template: ''
})
export class AdmUserTokenLoginDeletePopupComponent implements OnInit, OnDestroy {
  protected ngbModalRef: NgbModalRef;

  constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ admUserTokenLogin }) => {
      setTimeout(() => {
        this.ngbModalRef = this.modalService.open(AdmUserTokenLoginDeleteDialogComponent as Component, { size: 'lg', backdrop: 'static' });
        this.ngbModalRef.componentInstance.admUserTokenLogin = admUserTokenLogin;
        this.ngbModalRef.result.then(
          result => {
            this.router.navigate(['/adm-user-token-login', { outlets: { popup: null } }]);
            this.ngbModalRef = null;
          },
          reason => {
            this.router.navigate(['/adm-user-token-login', { outlets: { popup: null } }]);
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
