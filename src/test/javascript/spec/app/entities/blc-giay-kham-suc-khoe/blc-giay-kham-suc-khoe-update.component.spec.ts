/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { Observable, of } from 'rxjs';

import { GcsBlockchainApplicationTestModule } from '../../../test.module';
import { BlcGiayKhamSucKhoeUpdateComponent } from 'app/entities/blc-giay-kham-suc-khoe/blc-giay-kham-suc-khoe-update.component';
import { BlcGiayKhamSucKhoeService } from 'app/entities/blc-giay-kham-suc-khoe/blc-giay-kham-suc-khoe.service';
import { BlcGiayKhamSucKhoe } from 'app/shared/model/blc-giay-kham-suc-khoe.model';

describe('Component Tests', () => {
  describe('BlcGiayKhamSucKhoe Management Update Component', () => {
    let comp: BlcGiayKhamSucKhoeUpdateComponent;
    let fixture: ComponentFixture<BlcGiayKhamSucKhoeUpdateComponent>;
    let service: BlcGiayKhamSucKhoeService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [GcsBlockchainApplicationTestModule],
        declarations: [BlcGiayKhamSucKhoeUpdateComponent],
        providers: [FormBuilder]
      })
        .overrideTemplate(BlcGiayKhamSucKhoeUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(BlcGiayKhamSucKhoeUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(BlcGiayKhamSucKhoeService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new BlcGiayKhamSucKhoe(123);
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
        const entity = new BlcGiayKhamSucKhoe();
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
