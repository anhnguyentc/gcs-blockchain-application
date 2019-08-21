/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable, of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { GcsBlockchainApplicationTestModule } from '../../../test.module';
import { BlcGiayKhamSucKhoeDeleteDialogComponent } from 'app/entities/blc-giay-kham-suc-khoe/blc-giay-kham-suc-khoe-delete-dialog.component';
import { BlcGiayKhamSucKhoeService } from 'app/entities/blc-giay-kham-suc-khoe/blc-giay-kham-suc-khoe.service';

describe('Component Tests', () => {
  describe('BlcGiayKhamSucKhoe Management Delete Component', () => {
    let comp: BlcGiayKhamSucKhoeDeleteDialogComponent;
    let fixture: ComponentFixture<BlcGiayKhamSucKhoeDeleteDialogComponent>;
    let service: BlcGiayKhamSucKhoeService;
    let mockEventManager: any;
    let mockActiveModal: any;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [GcsBlockchainApplicationTestModule],
        declarations: [BlcGiayKhamSucKhoeDeleteDialogComponent]
      })
        .overrideTemplate(BlcGiayKhamSucKhoeDeleteDialogComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(BlcGiayKhamSucKhoeDeleteDialogComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(BlcGiayKhamSucKhoeService);
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
