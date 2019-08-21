/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { Observable, of } from 'rxjs';

import { GcsBlockchainApplicationTestModule } from '../../../test.module';
import { BlcRegisterPrivateKeyUpdateComponent } from 'app/entities/blc-register-private-key/blc-register-private-key-update.component';
import { BlcRegisterPrivateKeyService } from 'app/entities/blc-register-private-key/blc-register-private-key.service';
import { BlcRegisterPrivateKey } from 'app/shared/model/blc-register-private-key.model';

describe('Component Tests', () => {
  describe('BlcRegisterPrivateKey Management Update Component', () => {
    let comp: BlcRegisterPrivateKeyUpdateComponent;
    let fixture: ComponentFixture<BlcRegisterPrivateKeyUpdateComponent>;
    let service: BlcRegisterPrivateKeyService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [GcsBlockchainApplicationTestModule],
        declarations: [BlcRegisterPrivateKeyUpdateComponent],
        providers: [FormBuilder]
      })
        .overrideTemplate(BlcRegisterPrivateKeyUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(BlcRegisterPrivateKeyUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(BlcRegisterPrivateKeyService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new BlcRegisterPrivateKey(123);
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
        const entity = new BlcRegisterPrivateKey();
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
