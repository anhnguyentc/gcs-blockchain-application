/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable, of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { GcsBlockchainApplicationTestModule } from '../../../test.module';
import { BenhVienDeleteDialogComponent } from 'app/entities/benh-vien/benh-vien-delete-dialog.component';
import { BenhVienService } from 'app/entities/benh-vien/benh-vien.service';

describe('Component Tests', () => {
  describe('BenhVien Management Delete Component', () => {
    let comp: BenhVienDeleteDialogComponent;
    let fixture: ComponentFixture<BenhVienDeleteDialogComponent>;
    let service: BenhVienService;
    let mockEventManager: any;
    let mockActiveModal: any;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [GcsBlockchainApplicationTestModule],
        declarations: [BenhVienDeleteDialogComponent]
      })
        .overrideTemplate(BenhVienDeleteDialogComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(BenhVienDeleteDialogComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(BenhVienService);
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
