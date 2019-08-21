/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { Observable, of } from 'rxjs';

import { GcsBlockchainApplicationTestModule } from '../../../test.module';
import { DonViHanhChinhUpdateComponent } from 'app/entities/don-vi-hanh-chinh/don-vi-hanh-chinh-update.component';
import { DonViHanhChinhService } from 'app/entities/don-vi-hanh-chinh/don-vi-hanh-chinh.service';
import { DonViHanhChinh } from 'app/shared/model/don-vi-hanh-chinh.model';

describe('Component Tests', () => {
  describe('DonViHanhChinh Management Update Component', () => {
    let comp: DonViHanhChinhUpdateComponent;
    let fixture: ComponentFixture<DonViHanhChinhUpdateComponent>;
    let service: DonViHanhChinhService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [GcsBlockchainApplicationTestModule],
        declarations: [DonViHanhChinhUpdateComponent],
        providers: [FormBuilder]
      })
        .overrideTemplate(DonViHanhChinhUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(DonViHanhChinhUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(DonViHanhChinhService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new DonViHanhChinh(123);
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
        const entity = new DonViHanhChinh();
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
