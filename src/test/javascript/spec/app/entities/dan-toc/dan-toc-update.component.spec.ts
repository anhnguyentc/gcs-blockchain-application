/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { Observable, of } from 'rxjs';

import { GcsBlockchainApplicationTestModule } from '../../../test.module';
import { DanTocUpdateComponent } from 'app/entities/dan-toc/dan-toc-update.component';
import { DanTocService } from 'app/entities/dan-toc/dan-toc.service';
import { DanToc } from 'app/shared/model/dan-toc.model';

describe('Component Tests', () => {
  describe('DanToc Management Update Component', () => {
    let comp: DanTocUpdateComponent;
    let fixture: ComponentFixture<DanTocUpdateComponent>;
    let service: DanTocService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [GcsBlockchainApplicationTestModule],
        declarations: [DanTocUpdateComponent],
        providers: [FormBuilder]
      })
        .overrideTemplate(DanTocUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(DanTocUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(DanTocService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new DanToc(123);
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
        const entity = new DanToc();
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
