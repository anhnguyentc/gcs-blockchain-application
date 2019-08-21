/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable, of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { GcsBlockchainApplicationTestModule } from '../../../test.module';
import { AdmUserTokenLoginDeleteDialogComponent } from 'app/entities/adm-user-token-login/adm-user-token-login-delete-dialog.component';
import { AdmUserTokenLoginService } from 'app/entities/adm-user-token-login/adm-user-token-login.service';

describe('Component Tests', () => {
  describe('AdmUserTokenLogin Management Delete Component', () => {
    let comp: AdmUserTokenLoginDeleteDialogComponent;
    let fixture: ComponentFixture<AdmUserTokenLoginDeleteDialogComponent>;
    let service: AdmUserTokenLoginService;
    let mockEventManager: any;
    let mockActiveModal: any;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [GcsBlockchainApplicationTestModule],
        declarations: [AdmUserTokenLoginDeleteDialogComponent]
      })
        .overrideTemplate(AdmUserTokenLoginDeleteDialogComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(AdmUserTokenLoginDeleteDialogComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(AdmUserTokenLoginService);
      mockEventManager = fixture.debugElement.injector.get(JhiEventManager);
      mockActiveModal = fixture.debugElement.injector.get(NgbActiveModal);
    });

    describe('confirmDelete', () => {
      it('Should call delete service on confirmDelete', inject(
        [],
        fakeAsync(() => {
          // GIVEN
          spyOn(service, 'delete').and.returnValue(of({}));

          // WHEN
          comp.confirmDelete(123);
          tick();

          // THEN
          expect(service.delete).toHaveBeenCalledWith(123);
          expect(mockActiveModal.dismissSpy).toHaveBeenCalled();
          expect(mockEventManager.broadcastSpy).toHaveBeenCalled();
        })
      ));
    });
  });
});
