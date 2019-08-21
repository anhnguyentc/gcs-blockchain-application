/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable, of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { GcsBlockchainApplicationTestModule } from '../../../test.module';
import { BlcRegisterPrivateKeyDeleteDialogComponent } from 'app/entities/blc-register-private-key/blc-register-private-key-delete-dialog.component';
import { BlcRegisterPrivateKeyService } from 'app/entities/blc-register-private-key/blc-register-private-key.service';

describe('Component Tests', () => {
  describe('BlcRegisterPrivateKey Management Delete Component', () => {
    let comp: BlcRegisterPrivateKeyDeleteDialogComponent;
    let fixture: ComponentFixture<BlcRegisterPrivateKeyDeleteDialogComponent>;
    let service: BlcRegisterPrivateKeyService;
    let mockEventManager: any;
    let mockActiveModal: any;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [GcsBlockchainApplicationTestModule],
        declarations: [BlcRegisterPrivateKeyDeleteDialogComponent]
      })
        .overrideTemplate(BlcRegisterPrivateKeyDeleteDialogComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(BlcRegisterPrivateKeyDeleteDialogComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(BlcRegisterPrivateKeyService);
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
