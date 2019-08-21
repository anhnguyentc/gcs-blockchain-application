/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable, of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { GcsBlockchainApplicationTestModule } from '../../../test.module';
import { DanTocDeleteDialogComponent } from 'app/entities/dan-toc/dan-toc-delete-dialog.component';
import { DanTocService } from 'app/entities/dan-toc/dan-toc.service';

describe('Component Tests', () => {
  describe('DanToc Management Delete Component', () => {
    let comp: DanTocDeleteDialogComponent;
    let fixture: ComponentFixture<DanTocDeleteDialogComponent>;
    let service: DanTocService;
    let mockEventManager: any;
    let mockActiveModal: any;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [GcsBlockchainApplicationTestModule],
        declarations: [DanTocDeleteDialogComponent]
      })
        .overrideTemplate(DanTocDeleteDialogComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(DanTocDeleteDialogComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(DanTocService);
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
