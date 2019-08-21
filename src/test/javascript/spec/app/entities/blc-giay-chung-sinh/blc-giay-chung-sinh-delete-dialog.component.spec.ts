/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable, of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { GcsBlockchainApplicationTestModule } from '../../../test.module';
import { BlcGiayChungSinhDeleteDialogComponent } from 'app/entities/blc-giay-chung-sinh/blc-giay-chung-sinh-delete-dialog.component';
import { BlcGiayChungSinhService } from 'app/entities/blc-giay-chung-sinh/blc-giay-chung-sinh.service';

describe('Component Tests', () => {
  describe('BlcGiayChungSinh Management Delete Component', () => {
    let comp: BlcGiayChungSinhDeleteDialogComponent;
    let fixture: ComponentFixture<BlcGiayChungSinhDeleteDialogComponent>;
    let service: BlcGiayChungSinhService;
    let mockEventManager: any;
    let mockActiveModal: any;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [GcsBlockchainApplicationTestModule],
        declarations: [BlcGiayChungSinhDeleteDialogComponent]
      })
        .overrideTemplate(BlcGiayChungSinhDeleteDialogComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(BlcGiayChungSinhDeleteDialogComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(BlcGiayChungSinhService);
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
