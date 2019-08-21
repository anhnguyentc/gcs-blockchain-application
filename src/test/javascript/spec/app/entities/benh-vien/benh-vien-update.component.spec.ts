/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { Observable, of } from 'rxjs';

import { GcsBlockchainApplicationTestModule } from '../../../test.module';
import { BenhVienUpdateComponent } from 'app/entities/benh-vien/benh-vien-update.component';
import { BenhVienService } from 'app/entities/benh-vien/benh-vien.service';
import { BenhVien } from 'app/shared/model/benh-vien.model';

describe('Component Tests', () => {
  describe('BenhVien Management Update Component', () => {
    let comp: BenhVienUpdateComponent;
    let fixture: ComponentFixture<BenhVienUpdateComponent>;
    let service: BenhVienService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [GcsBlockchainApplicationTestModule],
        declarations: [BenhVienUpdateComponent],
        providers: [FormBuilder]
      })
        .overrideTemplate(BenhVienUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(BenhVienUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(BenhVienService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new BenhVien(123);
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
        const entity = new BenhVien();
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
