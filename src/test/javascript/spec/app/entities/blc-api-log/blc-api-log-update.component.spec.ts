/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { Observable, of } from 'rxjs';

import { GcsBlockchainApplicationTestModule } from '../../../test.module';
import { BlcAPILogUpdateComponent } from 'app/entities/blc-api-log/blc-api-log-update.component';
import { BlcAPILogService } from 'app/entities/blc-api-log/blc-api-log.service';
import { BlcAPILog } from 'app/shared/model/blc-api-log.model';

describe('Component Tests', () => {
  describe('BlcAPILog Management Update Component', () => {
    let comp: BlcAPILogUpdateComponent;
    let fixture: ComponentFixture<BlcAPILogUpdateComponent>;
    let service: BlcAPILogService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [GcsBlockchainApplicationTestModule],
        declarations: [BlcAPILogUpdateComponent],
        providers: [FormBuilder]
      })
        .overrideTemplate(BlcAPILogUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(BlcAPILogUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(BlcAPILogService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new BlcAPILog(123);
        spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
        comp.updateForm(entity);
        // WHEN
        comp.save();
        tick(); // simulate async

        // THEN
        expect(service.update).toHaveBeenCalledWith(entity);
        expect(comp.isSaving).toEqual(false);
      }));

      it('Should call create service on save for new entity', fakeAsync(() => {
        // GIVEN
        const entity = new BlcAPILog();
        spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
        comp.updateForm(entity);
        // WHEN
        comp.save();
        tick(); // simulate async

        // THEN
        expect(service.create).toHaveBeenCalledWith(entity);
        expect(comp.isSaving).toEqual(false);
      }));
    });
  });
});
