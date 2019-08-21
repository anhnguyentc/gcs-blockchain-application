/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { Observable, of } from 'rxjs';

import { GcsBlockchainApplicationTestModule } from '../../../test.module';
import { AdmUserTokenLoginUpdateComponent } from 'app/entities/adm-user-token-login/adm-user-token-login-update.component';
import { AdmUserTokenLoginService } from 'app/entities/adm-user-token-login/adm-user-token-login.service';
import { AdmUserTokenLogin } from 'app/shared/model/adm-user-token-login.model';

describe('Component Tests', () => {
  describe('AdmUserTokenLogin Management Update Component', () => {
    let comp: AdmUserTokenLoginUpdateComponent;
    let fixture: ComponentFixture<AdmUserTokenLoginUpdateComponent>;
    let service: AdmUserTokenLoginService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [GcsBlockchainApplicationTestModule],
        declarations: [AdmUserTokenLoginUpdateComponent],
        providers: [FormBuilder]
      })
        .overrideTemplate(AdmUserTokenLoginUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(AdmUserTokenLoginUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(AdmUserTokenLoginService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new AdmUserTokenLogin(123);
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
        const entity = new AdmUserTokenLogin();
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
