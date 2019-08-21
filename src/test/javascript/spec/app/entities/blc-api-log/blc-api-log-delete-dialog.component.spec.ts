/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable, of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { GcsBlockchainApplicationTestModule } from '../../../test.module';
import { BlcAPILogDeleteDialogComponent } from 'app/entities/blc-api-log/blc-api-log-delete-dialog.component';
import { BlcAPILogService } from 'app/entities/blc-api-log/blc-api-log.service';

describe('Component Tests', () => {
  describe('BlcAPILog Management Delete Component', () => {
    let comp: BlcAPILogDeleteDialogComponent;
    let fixture: ComponentFixture<BlcAPILogDeleteDialogComponent>;
    let service: BlcAPILogService;
    let mockEventManager: any;
    let mockActiveModal: any;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [GcsBlockchainApplicationTestModule],
        declarations: [BlcAPILogDeleteDialogComponent]
      })
        .overrideTemplate(BlcAPILogDeleteDialogComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(BlcAPILogDeleteDialogComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(BlcAPILogService);
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
